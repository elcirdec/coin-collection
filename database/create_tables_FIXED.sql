-- ========================================
-- COIN COLLECTION DATABASE SCHEMA
-- ========================================
-- Version: 1.1 (Fixed - Added pos_prix_achat column)
-- Date: 2026-01-31

-- ========================================
-- 1. CURRENCY TABLE (Devise)
-- ========================================
CREATE TABLE devise (
    cur_id SERIAL PRIMARY KEY,
    cur_code VARCHAR(5) UNIQUE NOT NULL,
    cur_nom VARCHAR(50) NOT NULL,
    cur_symbole VARCHAR(5)
);

-- ========================================
-- 2. COIN TYPE TABLE (TypePiece)
-- ========================================
CREATE TABLE type_piece (
    ct_id SERIAL PRIMARY KEY,
    ct_label VARCHAR(255) NOT NULL,
    ct_valeur DECIMAL(10,2) NOT NULL,
    ct_pays VARCHAR(100) NOT NULL,
    ct_commem BOOLEAN DEFAULT FALSE,
    ct_desc TEXT,
    ct_img_avers VARCHAR(255),
    ct_img_rev VARCHAR(255),
    ct_img_drap VARCHAR(255),
    cur_id INT NOT NULL,
    CONSTRAINT fk_devise FOREIGN KEY (cur_id) REFERENCES devise(cur_id)
);

-- ========================================
-- 3. MINTING TABLE (Frappe)
-- ========================================
CREATE TABLE frappe (
    mt_id SERIAL PRIMARY KEY,
    mt_annee INT NOT NULL,
    mt_tirage BIGINT,
    mt_atelier VARCHAR(10),
    ct_id INT NOT NULL,
    CONSTRAINT fk_type_piece FOREIGN KEY (ct_id) REFERENCES type_piece(ct_id)
);

-- ========================================
-- 4. USER TABLE (Utilisateur)
-- ========================================
CREATE TABLE utilisateur (
    usr_id SERIAL PRIMARY KEY,
    usr_pseudo VARCHAR(50) UNIQUE NOT NULL,
    usr_email VARCHAR(150) UNIQUE NOT NULL,
    usr_mdp VARCHAR(255) NOT NULL
);

-- ========================================
-- 5. POSSESSION TABLE (Collection)
-- ========================================
-- FIXED: Added pos_prix_achat column
CREATE TABLE possession (
    pos_id SERIAL PRIMARY KEY,
    pos_quantite INT NOT NULL DEFAULT 1,
    pos_etat VARCHAR(20),
    pos_date_acq DATE,
    pos_date_crea TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    pos_prix_achat DECIMAL(10,2),  -- ← AJOUTÉ: Purchase price
    usr_id INT NOT NULL,
    ct_id INT NOT NULL,
    mt_id INT,
    CONSTRAINT fk_utilisateur FOREIGN KEY (usr_id) REFERENCES utilisateur(usr_id),
    CONSTRAINT fk_pos_type FOREIGN KEY (ct_id) REFERENCES type_piece(ct_id),
    CONSTRAINT fk_pos_frappe FOREIGN KEY (mt_id) REFERENCES frappe(mt_id)
);

-- ========================================
-- INDEXES FOR PERFORMANCE
-- ========================================
-- These indexes will speed up common queries

-- Index on possession queries by user
CREATE INDEX idx_possession_user ON possession(usr_id);

-- Index on possession queries by coin type
CREATE INDEX idx_possession_cointype ON possession(ct_id);

-- Index on frappe queries by coin type
CREATE INDEX idx_frappe_cointype ON frappe(ct_id);

-- Index on frappe queries by year
CREATE INDEX idx_frappe_year ON frappe(mt_annee);

-- ========================================
-- COMMENTS (Documentation in database)
-- ========================================
COMMENT ON TABLE devise IS 'Currency table (EUR, USD, CHF, etc.)';
COMMENT ON TABLE type_piece IS 'Coin type/design (e.g., 2€ France Arbre de vie)';
COMMENT ON TABLE frappe IS 'Specific minting/emission (year, mintage count, mint mark)';
COMMENT ON TABLE utilisateur IS 'Registered users of the application';
COMMENT ON TABLE possession IS 'User coin collection (links user to coins)';

COMMENT ON COLUMN possession.pos_etat IS 'Condition: UNC, BU, BE, CIRCULATED, or NULL';
COMMENT ON COLUMN possession.mt_id IS 'Nullable: NULL for Quick Add, set for Expert Add';
COMMENT ON COLUMN possession.pos_prix_achat IS 'Purchase price paid by user (optional)';
