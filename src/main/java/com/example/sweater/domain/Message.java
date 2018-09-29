package com.example.sweater.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Hincu Andrei (andreih1981@gmail.com)on 10.08.2018.
 * @version $Id$.
 * @since 0.1.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(of = {"id"})
@Entity
@Table(indexes = {
        @Index(name = "idx_id_text", columnList = "id, text, tag")
})
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String tag;
    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Message(String text, String tag, User user) {
        this.author = user;
        this.text = text;
        this.tag = tag;
    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

}
