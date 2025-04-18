package edu.kh.todo.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.kh.todo.model.dto.Todo;

/*
 * @Mapper 
 * - MyBaris 에서 제공하는 어노테이션
 * - MyBaris 에서 SQL 과 Java 메서드를 연결해주는 인터페이스 (Mapper)의
 *   구현체를 스프링의 Bean 으로 등록할 수 있게 해주는 어노테이션.
 * 
 * 이 어노테이션을 붙이면 Spring 이 Mapper 인터페이스를 인식하고, 자동으로 구현체를 생성해줌.
 * -> 이 구현체가 Bean 으로 등록됨
 * 
 * - 해당 어노테이션이 작성된 인터페이스는
 *   namespace 에 해당 인퍼테이스가 작성된 ampper.xml파일과 연결되어
 *   SQL 호출/수행/결과 반환 가능
 * 
 * */

@Mapper
public interface TodoMapper {

	// Mapper의 메서드명 == mapper.xml 파일 내 태그의 id
	// -> 메서드 명과 sql 구문 중 id가 같은 태그가 서로 연결된다!

	String testTitle();

	List<Todo> selectAll();

	int getCompleteCount();

	int addTodo(Todo todo);

	Todo todoDetail(int todoNo);

	int changeComplete(Todo todo);

	int todoUpdate(Todo todo);

	int todoDelete(int todoNo);
	

}
