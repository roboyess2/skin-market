package com.skinmarket.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordUtil {
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String hash(String plain) {
        // Генерируем соль в hex (без проблемных символов)
        byte[] salt = new byte[16];
        RANDOM.nextBytes(salt);
        String saltHex = bytesToHex(salt);

        String hash = sha256(plain + saltHex);
        System.out.println("=== HASH ===");
        System.out.println("Plain: " + plain);
        System.out.println("Salt: " + saltHex);
        System.out.println("Hash: " + hash);
        System.out.println("Stored: " + saltHex + "$" + hash);
        return saltHex + "$" + hash;
    }

    public static boolean check(String plain, String stored) {
        System.out.println("=== CHECK ===");
        System.out.println("Plain: " + plain);
        System.out.println("Stored: " + stored);

        if (stored == null || !stored.contains("$")) {
            System.out.println("ERROR: Invalid stored format");
            return false;
        }

        String[] parts = stored.split("\\$");
        if (parts.length != 2) {
            System.out.println("ERROR: Invalid stored format, parts=" + parts.length);
            return false;
        }

        String salt = parts[0];
        String oldHash = parts[1];
        String newHash = sha256(plain + salt);

        System.out.println("Salt: " + salt);
        System.out.println("Old hash: " + oldHash);
        System.out.println("New hash: " + newHash);
        System.out.println("Match: " + oldHash.equals(newHash));

        return oldHash.equals(newHash);
    }

    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(input.getBytes());
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 not available", e);
        }
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}