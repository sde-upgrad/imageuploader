package com.upgrad.ImageHoster.common;

import com.upgrad.ImageHoster.model.Tag;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.util.List;

@SuppressWarnings("unchecked")
public class TagManager extends SessionManager {

    public Tag createTag(Tag tag) {
        Session session = openSession();
        session.save(tag);
        commitSession(session);
        return tag;
    }

    public Tag findTag(String tagName) {
        Session session = openSession();

        Criteria criteria = session.createCriteria(Tag.class);
        Tag tag = (Tag) criteria
                .add(Restrictions.eq("name", tagName))
                .uniqueResult();

        return tag;
    }

    public List<Tag> getAllTags() {

        //Start a session, then get the list of all tags into "tags" by using session.createCriteria and then finally commit the session

        Session session = openSession();
        List<Tag> tags = session.createCriteria(Tag.class).list();
        commitSession(session);

        return tags;
    }
}
