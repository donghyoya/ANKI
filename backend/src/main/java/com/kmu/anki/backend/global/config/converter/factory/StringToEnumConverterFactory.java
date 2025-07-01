package com.kmu.anki.backend.global.config.converter.factory;

import com.kmu.anki.backend.domain.card.enums.CardLevel;
import com.kmu.anki.backend.domain.card.enums.CardTopicEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;

import java.util.HashMap;
import java.util.Map;

public class StringToEnumConverterFactory implements ConverterFactory<String, Enum<?>> {
    private final Map<Class, StringToEnumConverter> map = new HashMap();
    @Override
    public <T extends Enum<?>> Converter<String, T> getConverter(Class<T> targetType) {
        if(!map.containsKey(targetType)){
            map.put(targetType, new StringToEnumConverter(targetType));
        }
        return map.get(targetType);
    }

    public CardTopicEnums convertTopic(String query){
        return getConverter(CardTopicEnums.class).convert(query);
    }

    public CardLevel convertLevel(String query){
        return getConverter(CardLevel.class).convert(query);
    }


    @RequiredArgsConstructor
    private static class StringToEnumConverter<T extends Enum<T>> implements Converter<String, Enum<T>>{
        private final Class<T> enumType;

        @Override
        public Enum<T> convert(String source) {
            if(source.isEmpty()){
                return null;
            }
            String trim = source.trim();
            T t;
            try{
                t = Enum.valueOf(enumType, trim.toLowerCase());
            }catch (IllegalArgumentException e){
                t = Enum.valueOf(enumType, trim.toUpperCase());
            }
            return t;
        }
    }
}
