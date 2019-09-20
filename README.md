# Programmers.2017_Java_KAKAOBLINDRECRUITMENT_NewsClustering

## 프로그래머스 2017 카카오 블라인드 리쿠르트 > 뉴스클러스터링

### 1. 문제설명

문제: https://programmers.co.kr/learn/courses/30/lessons/17677

input으로 두개의 String str1, str2를 받아온다. 두 문자열의 유사도를 확장시킨 "자카드 유사도" 방법으로 찾아낸다. 문자열당 두 글자씩 끊어서 다중 집합을 만들어 생성된 두 다중집합의 교집합의 크기, 합집합의 크기를 계산한다. 이때, (교집합의 크기 / 합집합의 크기) 를 유사도라 하며 실수로 다루기 쉽도록 65536을 곱한 후에 소수점 아래를 버리고 정수부만 return하는 문제.

두 글자씩 끊어서 만든 다중집합의 원소에는 조건이 존재한다. 
* 영문은 대소문자를 구분하지 않는다.
* 영문자로 된 글자 쌍만 유효하고, 기타 공백이나 숫자, 특수 문자가 들어있는 경우는 그 글자쌍을 버린다.

### 2. 풀이

입력으로 받아온 두 문자열에 대하여 두 글자씩 끊어서 만든 다중집합을 생성한다.

이때 전처리로 모든 영문자를 소문자로 만들며,

```java
str1 = str1.toLowerCase();
```

두 글자씩 끊어 저장할 때 영문자로 된 글자일때만 추가한다. 중복 집합이므로 HashMap을 이용하여 해당 글자의 중복 횟수를 함께 저장한다.

```java
if (Character.isAlphabetic(str.charAt(0)) && Character.isAlphabetic(str.charAt(1))) 
{
  if (map1.containsKey(str)) {
        map1.put(str, map1.get(str) + 1);
      } else {
        map1.put(str, 1);
      }
}
```

교집합과 합집합의 원소의 개수는 위 작업으로 생성된 모든 문자의 집합을 만들어 카운트한다.

```java
int unionCount = 0;
int intersectionCount = 0;
HashSet<String> union = new HashSet<>();
union.addAll(map1.keySet());
union.addAll(map2.keySet());
for (Iterator<String> it = union.iterator(); it.hasNext(); ) {
  String key = it.next();
  if (map1.containsKey(key) && map2.containsKey(key)) {
    int s1 = map1.get(key);
    int s2 = map2.get(key);
    intersectionCount += Math.min(s1, s2);
    unionCount += Math.max(s1, s2);
  } else if (map1.containsKey(key)) {
    unionCount += map1.get(key);
  } else if (map2.containsKey(key)) {
    unionCount += map2.get(key);
  }
}
```


## 3. 주의할 점

테스트 케이스 4번 경우에는 두 문자열에서 두 글자씩 끊어서 생성되는 글자가 존재하지 않는다.
*두 글자씩 끊어 만든 문자가 모두 영문자로 이루어지지 않았기 때문에*
``` java
String str1 = "E=M*C^2";
String str2 = "e=m*c^2"; // return 65536
```

이 경우를 위해 만들어진 두 집합이 모두 비어있다면 곱해주어야 할 숫자 65536를 return한다.

```java
if (map1.isEmpty() && map2.isEmpty()) {
  return 65536;
}
```
