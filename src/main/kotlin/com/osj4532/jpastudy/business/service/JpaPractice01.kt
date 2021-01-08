package com.osj4532.jpastudy.business.service

import com.osj4532.jpastudy.entity.BaseEntity
import com.osj4532.jpastudy.entity.practice.ExMember
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.EntityManagerFactory

@Service
class JpaPractice01(
        val emf: EntityManagerFactory
) {
    fun jpaPractice_01() {
        // [entity manager] - create
        val em = emf.createEntityManager()
        // [transaction] - get
        val tx = em.transaction

        try {
            // [transaction] - start
            tx.begin()
            // [business logic] - start
            logic01(em)
            // [transaction] - commit
            tx.commit()
        } catch (e: Exception) {
            // [transaction] - rollback
            tx.rollback()
        } finally {
            // [entity manager] - end
            em.close()
        }
        // [entity manager factory] - end
//        emf.close()
    }

    fun logic01(em: EntityManager) {
        val id = "id1"
        val member = ExMember(id = id, username = "osj", age = 27)

        em.persist(member)

        member.age = 28

        val findMember = em.find(ExMember::class.java, id)
        println("findMember=${findMember.username}, ${findMember.age}")

        val members: List<ExMember> = em.createQuery("select m from ExMember m", ExMember::class.java).resultList
        println("members.size=${members.size}")

        em.remove(member)
    }
}