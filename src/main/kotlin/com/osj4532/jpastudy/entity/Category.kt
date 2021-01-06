package com.osj4532.jpastudy.entity

import javax.persistence.*

@Entity
@Table(name = "CATEGORY")
data class Category (
        @Id
        @GeneratedValue
        @Column(name = "CATEGORY_ID")
        val id: Long,

        var name: String,

        // 다대다 관계는 키만 있을 경우는 jpa가 알아서 관리하지만 컬럼이 추가 될 경우 사용할수 없게 된다.
        // 따라서 관계 테이블을 만들고 entity 를 따로 만들어서 관리하는 것을 권장함
        @ManyToMany
        @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = [JoinColumn(name = "CATEGORY_ID")],
            inverseJoinColumns = [JoinColumn(name = "ITEM_ID")])
        var items: MutableList<Item> = mutableListOf(),

        @ManyToOne
        @JoinColumn(name = "PARENT_ID")
        var parent: Category?,

        @OneToMany(mappedBy = "parent")
        var child: MutableList<Category> = mutableListOf()
)