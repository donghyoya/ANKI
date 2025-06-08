package com.kmu.anki.backend.global.config.search;

import org.apache.lucene.analysis.ko.KoreanTokenizerFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;

public class KoreanLuceneAnalysisConfigurerImpl implements LuceneAnalysisConfigurer {
    @Override
    public void configure(LuceneAnalysisConfigurationContext context) {
        context.analyzer("korean-analysis").custom()
                .tokenizer(KoreanTokenizerFactory.class)               // Nori 토크나이저
                .tokenFilter(NGramFilterFactory.class)                 // N-gram 토큰화
                .param("minGramSize", "2")
                .param("maxGramSize", "3")
        ;
    }
}
