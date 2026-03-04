package com.coincollection.backend.repositories;

import com.coincollection.backend.models.Frappe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repository for accessing Frappe (Minting) data.
 * Provides CRUD operations and custom queries for mintings.
 */
@Repository
public interface FrappeRepository extends JpaRepository<Frappe, Long> {
    
    /**
     * Find all mintings for a specific coin type.
     * Example: Get all years for "2€ France Arbre de vie"
     * 
     * @param typePieceId ID of the coin type
     * @return List of mintings ordered by year (most recent first)
     */
    List<Frappe> findByTypePieceIdOrderByAnneeDesc(Long typePieceId);
    
    /**
     * Find all mintings for a specific year.
     * Useful for browsing "What was minted in 2023?"
     * 
     * @param annee Year of minting
     * @return List of mintings from that year
     */
    List<Frappe> findByAnnee(Integer annee);
    
    /**
     * Find mintings by year range.
     * Example: Get all Euro mintings from 1999-2002 (first years)
     * 
     * @param startYear Starting year (inclusive)
     * @param endYear Ending year (inclusive)
     * @return List of mintings in the range
     */
    List<Frappe> findByAnneeBetween(Integer startYear, Integer endYear);
    
    /**
     * Find a specific minting by coin type and year.
     * Used for "Expert Add" when user selects year from dropdown.
     * 
     * @param typePieceId Coin type ID
     * @param annee Year
     * @return List of mintings (may have multiple mint marks)
     */
    List<Frappe> findByTypePieceIdAndAnnee(Long typePieceId, Integer annee);
}