package com.github.marwin1991.keep_changelog.markdown;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MarkdownListInTableTest {

    @Test
    void givenEmptyMarkdownListInTable_whenToMarkdown_thenReturnEmptyString() {
        //given:
        MarkdownListInTable markdownListInTable = new MarkdownListInTable();
        //when:
        String result = markdownListInTable.toMarkdown();
        String toStringResult = markdownListInTable.toString();
        System.out.println(toStringResult);
        //then:
        assertEquals(StringUtils.EMPTY, result);
        assertEquals(result, toStringResult);
    }

    @Test
    void givenNotEmptyMarkdownListInTable_whenToMarkdown_thenReturnSameString() {
        //given:
        MarkdownListInTable markdownListInTable = new MarkdownListInTable();
        markdownListInTable.add("Add new functionality");
        //when:
        String result = markdownListInTable.toMarkdown();
        String toStringResult = markdownListInTable.toString();
        System.out.println(toStringResult);
        //then:
        assertEquals(result, toStringResult);
    }

    @Test
    void givenNullMarkdownListInTable_whenToMarkdown_thenReturnEmptyString() {
        //given:
        MarkdownListInTable markdownListInTable = new MarkdownListInTable();
        markdownListInTable.add(null);
        //when:
        String result = markdownListInTable.toMarkdown();
        String toStringResult = markdownListInTable.toString();
        System.out.println(toStringResult);
        //then:
        assertEquals(StringUtils.EMPTY, result);
        assertEquals(result, toStringResult);
    }
}
