    package com.example.finalproject.model.entity;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.sql.Timestamp;
    import java.util.Collection;
    import java.util.List;


    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Entity
    public class Brand {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        @Column(name = "id")
        private Long id;

        @Column(name = "name")
        private String name;

        @Column(name = "description")
        private String description;

        @Column(name = "created_at")
        private Timestamp createdAt;

        @Column(name = "updated_at")
        private Timestamp updatedAt;

//
//        @JsonIgnore
//        @OneToMany(mappedBy = "brand")
//        private Collection<Product> products;


    }
