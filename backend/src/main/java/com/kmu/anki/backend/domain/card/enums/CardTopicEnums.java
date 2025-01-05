package com.kmu.anki.backend.domain.card.enums;

public enum CardTopicEnums {
    CONCEPT("개념"),
    ECONOMY("경제"),
    SCIENCE("과학"),
    TRANSPORT("교통"),
    WEATHER("날씨"),
    NEWS("뉴스"),
    FEELING("느낌"),
    GRAMMAR_AND_LANGUAGE("문법과 언어"),
    CULTURE("문화"),
    HOSPITAL("병원"),
    LIFE("삶"),
    LIVING("생활"),
    PERSONALITY("성격"),
    NUMBER("수"),
    COMMUNICATION("소통"),
    TIME("시간"),
    FOOD("음식"),
    RELATIONSHIPS("인간관계"),
    NATURE("자연"),
    POLITICS("정치"),
    RELIGION("종교"),
    WORK("직장"),
    HOME("집"),
    FASHION_AND_APPEARANCE("패션과 외모"),
    SCHOOL("학교"),
    ACTION("행동"),
    ADMINISTRATION("행정");

    private final String value;

    // Constructor
    CardTopicEnums(String value) {
        this.value = value;
    }


    // Getter for the value
    public String getValue() {
        return value;
    }

    // Static method to get Topic from a string
    public static CardTopicEnums fromString(String text) {
        for (CardTopicEnums topic : CardTopicEnums.values()) {
            if (topic.value.equals(text)) {
                return topic;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + text);
    }

    public static Long findTopicId(String topic) {
        CardTopicEnums[] topics = CardTopicEnums.values();
        for(int i=0;i<topics.length;i++){
            if (topics[i].value.equals(topic)){
                return (long) i;
            }
        }
        return -1L;
    }

}
