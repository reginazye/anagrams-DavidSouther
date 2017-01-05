/*
 *  Copyright 2016 Google Inc. All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.google.engedu.anagrams;

import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class AnagramDictionary {

    private static final int MIN_NUM_ANAGRAMS = 5;
    private static final int DEFAULT_WORD_LENGTH = 3;
    private static final int MAX_WORD_LENGTH = 7;
    private Random random = new Random();
    private List<String> words = new ArrayList<>();


    private HashMap<String, ArrayList<String>> lettersToWord = new HashMap<>();

    public AnagramDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        String line;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            //
            //  Your code here
            //
            words.add(word);

            String temp = sortLetters(word);
            if (!lettersToWord.containsKey(temp)){
                lettersToWord.put(temp, new ArrayList<String>());
            }
                lettersToWord.get(temp).add(word);
        }
    }

    public boolean isGoodWord(String word, String base) {
        //
        // Your code here
        //
        if (base.equals(word)){
            return false;
        }
        return isAnagram(word.toUpperCase(), base.toUpperCase());
        }

    public List<String> getAnagrams(String targetWord) {

        String check =  sortLetters(targetWord);
        if (!lettersToWord.containsKey(check)){
            return new ArrayList<>();
        }
        return lettersToWord.get(check);
    }

    @VisibleForTesting
    static boolean isAnagram(String first, String second) {
        /**
         * 1. sort the words at char level first then compare one by one
         * 2. String builder
         * 3. chekcing letters (naively, so too efficient)
         */

        //
        // Your code here
        //
        if (first.length()==second.length()) {

            return sortLetters(first).equals(sortLetters(second));
        }
        return false;
    }

    @VisibleForTesting
    static String sortLetters(String input) {
        char[] chars = input.toCharArray();
        //
        // Your code here
        //
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<String> getAnagramsWithOneMoreLetter(String word) {
        ArrayList<String> result = new ArrayList<String>();

        for (int c='a';c<='z';c++){
            String temp = sortLetters(word+c);
            result.addAll(getAnagrams(temp));
        }
        return result;


    }

    public String pickGoodStarterWord() {
        //
        // Your code here
        //
        return "stop";
    }
}
