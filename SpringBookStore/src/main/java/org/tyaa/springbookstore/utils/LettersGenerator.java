package org.tyaa.springbookstore.utils;


import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LettersGenerator {

    private Character[] mLetters = new Character[26];
    //new Character[]{'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я'};

    @PostConstruct
    private void initLetters(){
        /* for (int i = 0; i < 26; i++) {
            mLetters[i] = (char)('a' + i);
        } */
        char currentChar = 'a';
        for (int i = 0; i < 26; i++) {
            mLetters[i] = currentChar++;
        }
    }

    public Character[] getLetters() {
        //System.out.println(mLetters);
        return mLetters;
    }
}
