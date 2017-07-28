package com.ivashchenko.practice2.task22;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class is used to display matches for regexp \B#(\d*|[A-F]*)@(\d*|[A-F]*)#\B.
 * @version 0.01
 * @author Alex Ivashchenko
 */
public class RegexHandler {
    private static List<RegexpMatch> matchRegexp(String text, String regexp) {
        System.out.println(text);
        System.out.println(regexp);
        List<RegexpMatch> matches = new LinkedList<>();
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String matchedString = text.substring(matcher.start(), matcher.end());
            matches.add(new RegexpMatch(matchedString, matcher.start(), matcher.end()));
        }
        return matches;
    }

    public static void checkMatching(String text, String regexp) {
        List<RegexpMatch> matches = matchRegexp(text, regexp);
        if (matches.isEmpty()) {
            System.out.println("No matches!");
            return;
        }
        int numberOfMatches = matches.size();
        System.out.println(String.format("%d matches found:", numberOfMatches));
        for (RegexpMatch regexpMatch: matches) {
            System.out.println("\t" + regexpMatch.toString());
        }
    }

    private static class RegexpMatch {
        String matchString;
        int startPosition;
        int endPosition;
        RegexpMatch(String matchString, int startPosition, int endPosition) {
            this.matchString = matchString;
            this.startPosition = startPosition;
            this.endPosition = endPosition;
        }

        @Override
        public String toString() {
            return matchString + String.format("  [%d:%d]", startPosition, endPosition);
        }
    }
}
