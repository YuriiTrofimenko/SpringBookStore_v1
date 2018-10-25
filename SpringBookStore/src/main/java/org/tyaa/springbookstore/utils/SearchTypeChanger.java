package org.tyaa.springbookstore.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.tyaa.springbookstore.utils.parts.SearchType;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import java.util.HashMap;
import java.util.Map;

/*Компонент изменения типа поиска*/

@Component
public class SearchTypeChanger {

    //Внедряем ресурс локализованных строк
    @Autowired
    private MessageSource msg;

    //карта пар: локализованная строка названия типа поиска + константа перечисления типа поиска
    private Map<String, SearchType> mSearchTypeList = new HashMap<String, SearchType>();
    // значение по-умолчанию
    private SearchType mSelectedSearchType = SearchType.TITLE;

    //Получаем весь список карт типов поиска
    public Map<String, SearchType> getSearchTypeList() {
        mSearchTypeList.clear();
        mSearchTypeList.put(msg.getMessage("author_name", null, FacesContext.getCurrentInstance().getViewRoot().getLocale()), SearchType.AUTHOR);
        mSearchTypeList.put(msg.getMessage("book_name", null, FacesContext.getCurrentInstance().getViewRoot().getLocale()), SearchType.TITLE);
        return mSearchTypeList;
    }

    //Получаем выбранный тип поиска
    //(устанавливается при инициализации полей компонента
    // или при выборе в веб-представлении)
    public SearchType getSelectedSearchType() {
        return mSelectedSearchType;
    }

    //Устанавливаем список карт типов поиска
    public void setSearchTypeList(Map<String, SearchType> searchTypeList) {
        this.mSearchTypeList = searchTypeList;
    }
}