package org.tyaa.springbookstore.dao;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.tyaa.springbookstore.entity.Genre;
import org.tyaa.springbookstore.utils.parts.SearchType;

import java.io.Serializable;

/**
 * Created by Yurii on 14.10.2016.
 */

@Component
@Scope("singleton")
public class SearchCriteria implements Serializable{

    private String mText;
    private SearchType mSearchType = SearchType.TITLE;
    private Character mLetter;
    private Genre mGenre;

    public String getText() {
        return mText;
    }

    public void setText(String _text) {
        mText = _text;
    }

    public SearchType getSearchType() {
        return mSearchType;
    }

    public void setSearchType(SearchType _searchType) {
        mSearchType = _searchType;
    }

    public Character getLetter() {
        return mLetter;
    }

    public void setLetter(Character _letter) {
        mLetter = _letter;
    }

    public Genre getGenre() {
        return mGenre;
    }

    public void setGenre(Genre _genre) {
        mGenre = _genre;
    }
}
