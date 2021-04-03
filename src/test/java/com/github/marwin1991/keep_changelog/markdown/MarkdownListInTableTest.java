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

        //then:
        assertEquals(StringUtils.EMPTY, result);
        assertEquals(result, toStringResult);
    }
}