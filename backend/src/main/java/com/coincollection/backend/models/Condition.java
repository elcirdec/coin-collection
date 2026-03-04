package com.coincollection.backend.models;

/**
 * Enumeration representing the physical condition of a coin.
 * Used to assess the collector and market value of a coin.
 */
public enum Condition {
    
    /**
     * UNC - Uncirculated
     * Coin has never been in circulation, pristine condition.
     */
    UNC("Uncirculated"),
    
    /**
     * BU - Brilliant Uncirculated
     * Coin specially produced for collectors with superior finish.
     */
    BU("Brilliant Uncirculated"),
    
    /**
     * BE - Belle Épreuve (Proof)
     * Highest quality coin, mirror-like finish, limited production.
     */
    BE("Proof - Belle Épreuve"),
    
    /**
     * CIRCULATED - Has been in circulation
     * Normal wear from everyday use.
     */
    CIRCULATED("Circulated");
    
    private final String description;
    
    /**
     * Constructor for Condition enum.
     * @param description Human-readable description of the condition
     */
    Condition(String description) {
        this.description = description;
    }
    
    /**
     * Get the human-readable description.
     * @return Description of the condition
     */
    public String getDescription() {
        return description;
    }
}