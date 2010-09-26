/**
 * Copyright 2010 David L. Whitehurst
 * 
 * Licensed under the Apache License, Version 2.0 
 * (the "License"); You may not use this file except 
 * in compliance with the License. You may obtain a 
 * copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an 
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, 
 * either express or implied. See the License for the specific 
 * language governing permissions and limitations under the 
 * License.
 * 
 */
package org.dlw.ai.blackboard.knowledge;

import java.util.ArrayList;

import javax.persistence.*;

import org.dlw.ai.blackboard.BlackboardContext;
import org.dlw.ai.blackboard.domain.Assumption;
import org.dlw.ai.blackboard.domain.Sentence;
import org.dlw.ai.blackboard.rule.Rule;

/**
 * This interface defines the signature knowledge source object. Any default
 * implementation e.g.
 * {@link org.dlw.ai.blackboard.knowledge.primitive.CommonPrefixKnowledgeSource}
 * extends {@link InferenceEngine} ultimately through the implementation of this
 * interface and the extension {@link org.dlw.ai.blackboard.BlackboardContext}
 * directly within the implementation.
 * 
 * @author <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 * @version 1.0.0-RC (hibernate-mysql branch)
 * 
 */
@Entity
@Table(name="knowledge_source")
public abstract class KnowledgeSource extends BlackboardContext implements InferenceEngine, Comparable<KnowledgeSource>{

    /**
     * unique serial identifier
     */
    private static final long serialVersionUID = 3094361637466019949L;

    /**
     * Attribute id or primary key
     */
    private Long id;
    
    /**
     * Attribute name of KnowledgeSource (type extension)
     */
    private String name;
    
    /**
     * Attribute priority
     */
    private Integer priority;

    /**
     * Attribute to hold rules for KnowledgeSource
     */
    private ArrayList<Rule> rules = new ArrayList<Rule>();
    
    /**
     * Reset knowledge source
     */
    public abstract void reset();

    /**
     * Find dependent knowledge sources and tell them to add, retract, etc. a
     * statement
     * 
     * @param direction
     * @param statement
     */
    public abstract void notifyDependents(String direction, Assumption statement);

    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public abstract int compareTo(KnowledgeSource o);

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.InferenceEngine#evaluate(org.dlw.ai.blackboard.domain.Sentence)
     */
    public void evaluate(Sentence sentence) {
    }

    /**
     * @param priority the priority to set
     */
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    /**
     * @return the priority
     */
    public Integer getPriority() {
        return priority;
    }

    /**
     * @param rules
     *            the rules to set
     */
    public void setRules(ArrayList<Rule> rules) {
        this.rules = rules;
    }

    /**
     * @return the rules
     */
    public ArrayList<Rule> getRules() {
        return rules;
    }

    /**
     * @return the id
     */
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.BlackboardContext#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return super.equals(o);
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.BlackboardContext#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.BlackboardContext#toString()
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @return the name
     */
    @Column(name="name",nullable=false,length=50)    
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
