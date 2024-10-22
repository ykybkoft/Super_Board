package study.supercodingboard.service.user;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class TokenStoreService {

    // A simple in-memory blacklist; this could be replaced with a database or Redis for persistence
    private final Set<String> tokenBlacklist = new HashSet<>();

    // Add a token to the blacklist
    public void addTokenToBlacklist(String token) {
        tokenBlacklist.add(token);
    }

    // Check if a token is blacklisted
    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklist.contains(token);
    }
}
