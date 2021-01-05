package com.osj4532.jpastudy.business.service

import com.osj4532.jpastudy.entity.Member
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
            logic(em)
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

    fun logic(em: EntityManager) {
        val id = "id1"
        val member = Member(id = id, username = "osj", age = 27)

        em.persist(member)

        member.age = 28

        val findMember = em.find(Member::class.java, id)
        println("findMember=${findMember.username}, ${findMember.age}")

        val members: List<Member> = em.createQuery("select m from Member m", Member::class.java).resultList
        println("members.size=${members.size}")

        em.remove(member)
    }
}