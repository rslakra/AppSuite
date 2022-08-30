package com.rslakra.core.bos;

/**
 * An article, such as a news article or piece of investigative report. Newspapers and magazines have articles of many
 * different types and this is intended to cover them all.
 * <p>
 * More general types Thing Entity CreativeWork
 * <p>
 * More specific types WikiArticle
 * <p>
 * Disjoint types VideoGame Book
 *
 * @author Rohtash Lakra
 * @created 1/27/22 5:55 PM
 */
public class Article {

    // Short description of a text document or webpage.
// Literal
    private String shortDescription;

    // List of all the URLs present in the document.
    private AnyUri allUrl;

    // The actual text body of the article. Plaintext version of a wikipedia article.
// Literal
    private String articleBody;

    // Inter-language links are links to the same content in other languages.
    private AnyUri otherLanguageUrl;

    // Full text or body of a wiki or other article, including wikitext, html, xml formatting.
// Literal
    private String richContent;

}
