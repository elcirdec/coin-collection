package com.coincollection.backend.repositories;

import com.coincollection.backend.models.Possession;
import com.coincollection.backend.models.Condition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing user coin collections.
 * Provides queries for adding, viewing, and managing possessions.
 */
@Repository
public interface PossessionRepository extends JpaRepository<Possession, Long> {
    
    /**
     * Get all coins in a user's collection.
     * Used for "My Collection" page.
     * 
     * @param utilisateurId User ID
     * @return List of all user's possessions
     */
    List<Possession> findByUtilisateurId(Long utilisateurId);
    
    /**
     * Check if user owns a specific coin type.
     * Used to display "Already in collection" badge.
     * 
     * @param utilisateurId User ID
     * @param typePieceId Coin type ID
     * @return List of possessions (may have multiple with different years/conditions)
     */
    List<Possession> findByUtilisateurIdAndTypePieceId(Long utilisateurId, Long typePieceId);
    
    /**
     * Find user's possession of a specific minting.
     * Example: Does user have "2€ France 2023"?
     * 
     * @param utilisateurId User ID
     * @param frappeId Minting ID
     * @return Optional possession
     */
    Optional<Possession> findByUtilisateurIdAndFrappeId(Long utilisateurId, Long frappeId);
    
    /**
     * Get all possessions with missing details (Quick Add).
     * Used for "Complete your collection" feature.
     * 
     * @param utilisateurId User ID
     * @return List of possessions where frappe is NULL
     */
    @Query("SELECT p FROM Possession p WHERE p.utilisateur.id = :userId AND p.frappe IS NULL")
    List<Possession> findIncompleteByUserId(@Param("userId") Long utilisateurId);
    
    /**
     * Get possessions by condition.
     * Example: Show all UNC coins in collection.
     * 
     * @param utilisateurId User ID
     * @param etat Condition
     * @return List of possessions matching condition
     */
    List<Possession> findByUtilisateurIdAndEtat(Long utilisateurId, Condition etat);
    
    /**
     * Count total coins in user's collection.
     * 
     * @param utilisateurId User ID
     * @return Total number of possession records (not quantity sum)
     */
    Long countByUtilisateurId(Long utilisateurId);
    
    /**
     * Get total quantity of coins owned (sum of all quantities).
     * Example: User has 3 records but quantity is 1+2+5 = 8 coins total.
     * 
     * @param utilisateurId User ID
     * @return Sum of all quantities
     */
    @Query("SELECT COALESCE(SUM(p.quantite), 0) FROM Possession p WHERE p.utilisateur.id = :userId")
    Integer getTotalCoinCount(@Param("userId") Long utilisateurId);
    
    /**
     * Find possessions by coin type (across all users).
     * Used for statistics: "This coin is owned by X collectors"
     * 
     * @param typePieceId Coin type ID
     * @return List of possessions
     */
    List<Possession> findByTypePieceId(Long typePieceId);
}