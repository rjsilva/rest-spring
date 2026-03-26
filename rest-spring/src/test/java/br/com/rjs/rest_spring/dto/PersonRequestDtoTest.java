package br.com.rjs.rest_spring.dto;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PersonRequestDtoTest {

    private static final Long ID = 1L;
    private static final String NAME = "Ronaldo";
    private static final String ADDRESS = "Rua Teste, 123";
    private static final Date BIRTHDAY = new Date(0L);
    private static final Character GENDER = 'M';

    @Test
    void shouldCreateRecordWithAllFields() {
        PersonRequestDto dto = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, GENDER);

        assertEquals(ID, dto.id());
        assertEquals(NAME, dto.name());
        assertEquals(ADDRESS, dto.address());
        assertEquals(BIRTHDAY, dto.birthDay());
        assertEquals(GENDER, dto.gender());
    }

    @Test
    void shouldCreateRecordWithNullFields() {
        PersonRequestDto dto = new PersonRequestDto(null, null, null, null, null);

        assertNull(dto.id());
        assertNull(dto.name());
        assertNull(dto.address());
        assertNull(dto.birthDay());
        assertNull(dto.gender());
    }

    @Test
    void shouldBeEqualWhenSameValues() {
        PersonRequestDto dto1 = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, GENDER);
        PersonRequestDto dto2 = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, GENDER);

        assertEquals(dto1, dto2);
        assertEquals(dto1.hashCode(), dto2.hashCode());
    }

    @Test
    void shouldNotBeEqualWhenDifferentId() {
        PersonRequestDto dto1 = new PersonRequestDto(1L, NAME, ADDRESS, BIRTHDAY, GENDER);
        PersonRequestDto dto2 = new PersonRequestDto(2L, NAME, ADDRESS, BIRTHDAY, GENDER);

        assertNotEquals(dto1, dto2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentName() {
        PersonRequestDto dto1 = new PersonRequestDto(ID, "Ronaldo", ADDRESS, BIRTHDAY, GENDER);
        PersonRequestDto dto2 = new PersonRequestDto(ID, "Maria", ADDRESS, BIRTHDAY, GENDER);

        assertNotEquals(dto1, dto2);
    }

    @Test
    void shouldNotBeEqualWhenDifferentGender() {
        PersonRequestDto dto1 = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, 'M');
        PersonRequestDto dto2 = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, 'F');

        assertNotEquals(dto1, dto2);
    }

    @Test
    void shouldNotBeEqualToNull() {
        PersonRequestDto dto = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, GENDER);

        assertNotEquals(null, dto);
    }

    @Test
    void shouldBeEqualToItself() {
        PersonRequestDto dto = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, GENDER);

        assertEquals(dto, dto);
    }

    @Test
    void toStringShouldContainAllFields() {
        PersonRequestDto dto = new PersonRequestDto(ID, NAME, ADDRESS, BIRTHDAY, GENDER);
        String result = dto.toString();

        assertTrue(result.contains(ID.toString()));
        assertTrue(result.contains(NAME));
        assertTrue(result.contains(ADDRESS));
        assertTrue(result.contains(GENDER.toString()));
    }

    @Test
    void shouldSupportFemaleGender() {
        PersonRequestDto dto = new PersonRequestDto(ID, "Maria", ADDRESS, BIRTHDAY, 'F');

        assertEquals('F', dto.gender());
    }

    @Test
    void shouldHandleNullBirthDay() {
        PersonRequestDto dto = new PersonRequestDto(ID, NAME, ADDRESS, null, GENDER);

        assertNull(dto.birthDay());
        assertEquals(NAME, dto.name());
    }
}
