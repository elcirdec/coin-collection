-- ========================================
-- TEST DATA FOR COIN COLLECTION DATABASE
-- ========================================
-- Version: 1.1 (FIXED - Corrected foreign key references)
-- Run after creating tables with create_tables_FIXED.sql
-- This provides sample data to test all entities

-- ========================================
-- 1. INSERT CURRENCIES (Devise)
-- ========================================
INSERT INTO devise (cur_code, cur_nom, cur_symbole) VALUES
('EUR', 'Euro', '€'),
('USD', 'Dollar américain', '$'),
('CHF', 'Franc suisse', 'CHF');

-- ========================================
-- 2. INSERT COIN TYPES (TypePiece)
-- ========================================
-- Regular circulation coins
INSERT INTO type_piece (ct_label, ct_valeur, ct_pays, ct_commem, ct_desc, ct_img_avers, ct_img_rev, ct_img_drap, cur_id) VALUES
-- France - Regular 2€
('Arbre de vie', 2.00, 'France', false, 
 'L''arbre de vie symbolise la croissance et le lien entre les nations européennes. Dessiné par Joaquin Jimenez.', 
 '/images/euro/fr-2e-arbre.jpg', 
 '/images/euro/common-2e.jpg', 
 '/images/flags/france.png', 
 1),

-- France - Regular 1€
('La Semeuse', 1.00, 'France', false, 
 'Représentation de Marianne semant des graines, symbole de la République française.', 
 '/images/euro/fr-1e-semeuse.jpg', 
 '/images/euro/common-1e.jpg', 
 '/images/flags/france.png', 
 1),

-- Germany - Regular 2€
('Bundesadler', 2.00, 'Germany', false, 
 'L''aigle fédéral allemand, symbole de la souveraineté et de l''unité.', 
 '/images/euro/de-2e-adler.jpg', 
 '/images/euro/common-2e.jpg', 
 '/images/flags/germany.png', 
 1),

-- Italy - Regular 2€
('Dante Alighieri', 2.00, 'Italy', false, 
 'Portrait de Dante Alighieri par Raphaël, célèbre poète italien.', 
 '/images/euro/it-2e-dante.jpg', 
 '/images/euro/common-2e.jpg', 
 '/images/flags/italy.png', 
 1),

-- France - Commemorative 2€ (Marie Curie)
('Marie Curie', 2.00, 'France', true, 
 'Pièce commémorative célébrant Marie Curie, première femme Prix Nobel et pionnière de la radioactivité.', 
 '/images/euro/fr-2e-curie.jpg', 
 '/images/euro/common-2e.jpg', 
 '/images/flags/france.png', 
 1);

-- ========================================
-- 3. INSERT MINTINGS (Frappe)
-- ========================================
-- France 2€ Arbre de vie (ct_id=1) - Multiple years
INSERT INTO frappe (mt_annee, mt_tirage, mt_atelier, ct_id) VALUES
(1999, 100000000, NULL, 1),  -- mt_id=1: First year
(2000, 120000000, NULL, 1),  -- mt_id=2
(2001, 150000000, NULL, 1),  -- mt_id=3
(2010, 80000000, NULL, 1),   -- mt_id=4
(2020, 50000000, NULL, 1),   -- mt_id=5
(2023, 45000000, NULL, 1);   -- mt_id=6

-- France 1€ La Semeuse (ct_id=2)
INSERT INTO frappe (mt_annee, mt_tirage, mt_atelier, ct_id) VALUES
(1999, 90000000, NULL, 2),   -- mt_id=7
(2010, 70000000, NULL, 2),   -- mt_id=8
(2023, 40000000, NULL, 2);   -- mt_id=9

-- Germany 2€ Bundesadler (ct_id=3) - With mint marks
INSERT INTO frappe (mt_annee, mt_tirage, mt_atelier, ct_id) VALUES
(2002, 80000000, 'A', 3),    -- mt_id=10: Berlin
(2002, 20000000, 'D', 3),    -- mt_id=11: Munich
(2002, 15000000, 'F', 3),    -- mt_id=12: Stuttgart
(2015, 60000000, 'A', 3),    -- mt_id=13
(2023, 50000000, 'A', 3);    -- mt_id=14

-- Italy 2€ Dante (ct_id=4)
INSERT INTO frappe (mt_annee, mt_tirage, mt_atelier, ct_id) VALUES
(2002, 70000000, 'R', 4),    -- mt_id=15: Rome
(2015, 55000000, 'R', 4),    -- mt_id=16
(2023, 48000000, 'R', 4);    -- mt_id=17

-- France 2€ Marie Curie (ct_id=5) - Commemorative (single year)
INSERT INTO frappe (mt_annee, mt_tirage, mt_atelier, ct_id) VALUES
(2023, 10000000, NULL, 5);   -- mt_id=18: Limited edition

-- ========================================
-- 4. INSERT TEST USERS (Utilisateur)
-- ========================================
-- NOTE: Passwords are plain text for testing ONLY!
-- In production, these MUST be hashed with BCrypt
INSERT INTO utilisateur (usr_pseudo, usr_email, usr_mdp) VALUES
('collector_max', 'max@example.com', 'password123'),
('coin_enthusiast', 'enthusiast@example.com', 'secure456'),
('euro_hunter', 'hunter@example.com', 'test789');

-- ========================================
-- 5. INSERT POSSESSIONS (Collection)
-- ========================================
-- User 1 (collector_max) - Mixed collection

-- Quick Add: 2€ France without details
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, usr_id, ct_id, mt_id) VALUES
(1, NULL, NULL, 1, 1, NULL);  -- pos_id=1: Quick add - no year specified

-- Expert Add: 2€ France 1999 UNC
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, pos_prix_achat, usr_id, ct_id, mt_id) VALUES
(1, 'UNC', '2023-01-15', 5.00, 1, 1, 1);  -- pos_id=2: France Arbre 1999

-- Expert Add: 2€ France 2023 BU
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, pos_prix_achat, usr_id, ct_id, mt_id) VALUES
(1, 'BU', '2023-12-20', 8.00, 1, 1, 6);  -- pos_id=3: France Arbre 2023

-- Circulated coin (multiple quantity)
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, usr_id, ct_id, mt_id) VALUES
(3, 'CIRCULATED', '2024-01-01', 1, 2, 7);  -- pos_id=4: 3x 1€ France 1999

-- Germany coin with mint mark
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, usr_id, ct_id, mt_id) VALUES
(1, 'UNC', '2023-06-10', 1, 3, 10);  -- pos_id=5: 2€ Germany 2002 Berlin (A)

-- Commemorative coin ✅ FIXED: mt_id=18 (was 17)
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, pos_prix_achat, usr_id, ct_id, mt_id) VALUES
(1, 'BE', '2023-11-01', 25.00, 1, 5, 18);  -- pos_id=6: 2€ Marie Curie 2023 BE

-- User 2 (coin_enthusiast) - Smaller collection

-- Quick add
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, usr_id, ct_id, mt_id) VALUES
(1, NULL, NULL, 2, 1, NULL);  -- pos_id=7: Quick add France

-- Italy coin ✅ FIXED: mt_id=15 (was 14)
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, usr_id, ct_id, mt_id) VALUES
(1, 'CIRCULATED', '2024-01-10', 2, 4, 15);  -- pos_id=8: 2€ Italy 2002 Rome

-- User 3 (euro_hunter) - Just started

-- Quick add
INSERT INTO possession (pos_quantite, pos_etat, pos_date_acq, usr_id, ct_id, mt_id) VALUES
(1, NULL, NULL, 3, 1, NULL);  -- pos_id=9: Quick add France

-- ========================================
-- VERIFICATION QUERIES
-- ========================================
-- Run these to verify data was inserted correctly:

-- Count verification:
-- SELECT COUNT(*) FROM devise;        -- Expected: 3
-- SELECT COUNT(*) FROM type_piece;    -- Expected: 5
-- SELECT COUNT(*) FROM frappe;        -- Expected: 18
-- SELECT COUNT(*) FROM utilisateur;   -- Expected: 3
-- SELECT COUNT(*) FROM possession;    -- Expected: 9

-- Check data integrity (possessions should match their coin types):
-- SELECT 
--     p.pos_id,
--     tp.ct_label AS coin_type,
--     tp.ct_pays AS country,
--     f.mt_annee AS year,
--     f.mt_atelier AS mint_mark,
--     p.pos_etat AS condition,
--     CASE 
--         WHEN f.ct_id IS NULL THEN '✓ Quick Add'  -- ← AJOUTÉ
--         WHEN f.ct_id = tp.ct_id THEN '✓ OK'
--         ELSE '✗ REAL ERROR'
--     END AS integrity_check
-- FROM possession p
-- JOIN type_piece tp ON p.ct_id = tp.ct_id
-- LEFT JOIN frappe f ON p.mt_id = f.mt_id
-- ORDER BY p.pos_id;

-- Get user collection with full details:
-- SELECT 
--     p.pos_id,
--     u.usr_pseudo,
--     tp.ct_label,
--     tp.ct_pays,
--     f.mt_annee,
--     f.mt_atelier,
--     p.pos_etat,
--     p.pos_quantite,
--     p.pos_prix_achat
-- FROM possession p
-- JOIN utilisateur u ON p.usr_id = u.usr_id
-- JOIN type_piece tp ON p.ct_id = tp.ct_id
-- LEFT JOIN frappe f ON p.mt_id = f.mt_id
-- WHERE u.usr_id = 1
-- ORDER BY tp.ct_valeur DESC, tp.ct_pays;