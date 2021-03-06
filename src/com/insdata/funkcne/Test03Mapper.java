package com.insdata.funkcne;

import com.insdata.funkcne.mapper.Mapper;

public class Test03Mapper {
    public static void main(String[] args) {

        Mapper<Integer> mapperFromInteger = Mapper.of(new Integer(888));

        String strInteger = mapperFromInteger.map((integer -> String.valueOf(integer)))
                .getMappableObject();
        Long longInteger = mapperFromInteger.map(integer -> new Long(integer)).getMappableObject();

        //lambda definovana pomocou method reference
        /*mozeme pouzit:
        * staticku metodu
        * instancnu metodu
        * instancnu metodu o ktorej sa rozhodne v runtime
        * konstruktor
        * */
        //staticka metoda
        String strIntegerCezMethodPointer = mapperFromInteger.map(Test03Mapper::staticMapToString).getMappableObject();
        //instancna metoda
        strIntegerCezMethodPointer = mapperFromInteger.map(new Test03Mapper()::mapToString).getMappableObject();
        //konstruktor
        Mapper<String> mapperFromString = Mapper.of("888");
        Integer integerFromStringCezMethodPointer = mapperFromString.map(Integer::new).getMappableObject();

        Mapper<Long> lng = Mapper.of(new Long(4434534534345L));
        String s = lng.transform((aLong -> aLong*0)).map((aLong -> aLong.toString())).getMappableObject();
    }

    private String mapToString(Integer integer){
        return String.valueOf(integer);
    }

    private static String staticMapToString(Integer integer){
        return String.valueOf(integer);
    }
}
