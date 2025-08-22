package com.tr.util;

import java.util.ArrayList;
import java.util.List;

public class KeywordMatcher {

    /**
     * Extracts keywords from resume content by splitting on spaces and commas.
     * Removes duplicates and empty entries.
     */
    public static List<String> extractKeywords(String content) 
    {
        if (content == null || content.isBlank()) 
        {
            return List.of();
        }

        String[] tokens = content.toLowerCase().split("[,\\s]+"); // split by comma or space
        List<String> keywords = new ArrayList<>();

        for (String token : tokens) 
        {
            if (!token.isBlank() && !keywords.contains(token)) 
            {
                keywords.add(token.trim());
            }
        }

        return keywords;
    }

    /**
     * Calculates skill match percentage between job-required skills and candidate resume.
     */
    public static int calculateMatchPercentage (List<String> jobSkills, List<String> candidateSkills) 
    {
        if (jobSkills == null || jobSkills.isEmpty() || candidateSkills == null) 
        {
            return 0;
        }

        int matches = 0;
        for (String skill : jobSkills) 
        {
            if (candidateSkills.contains(skill.toLowerCase())) 
            {
                matches++;
            }
        }

        return (matches * 100) / jobSkills.size();
    }
}
