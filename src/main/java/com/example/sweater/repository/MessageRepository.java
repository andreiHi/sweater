package com.example.sweater.repository;

import com.example.sweater.domain.Message;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.08.2018.
 * @version $Id$.
 * @since 0.1.
 */

public interface MessageRepository extends CrudRepository<Message, Integer> {

    List<Message> findByTag(String tag);

}
