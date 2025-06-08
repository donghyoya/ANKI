package com.kmu.anki.backend.global.config.search;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.ko.KoreanTokenizerFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramTokenizerFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
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

        context.analyzer("foreign-analysis").custom()
                .tokenizer(StandardTokenizerFactory.class) // 표준 토크나이저
                .tokenFilter(LowerCaseFilterFactory.class) // 소문자 변환
                .tokenFilter(ASCIIFoldingFilterFactory.class) // 악센트 제거
                .tokenFilter(StopFilterFactory.class); // 불용어 제거
    }
}
