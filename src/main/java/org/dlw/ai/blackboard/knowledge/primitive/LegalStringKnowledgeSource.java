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
package org.dlw.ai.blackboard.knowledge.primitive;

import org.dlw.ai.blackboard.domain.Assumption;
import org.dlw.ai.blackboard.domain.Sentence;
import org.dlw.ai.blackboard.knowledge.KnowledgeSource;
import org.dlw.ai.blackboard.knowledge.KnowledgeSourceConstants;

/**
 * @author <a href="mailto:dlwhitehurst@gmail.com">David L. Whitehurst</a>
 * @version 1.0.0-RC
 * 
 */
public class LegalStringKnowledgeSource extends StringKnowledgeSource {

    
    /**
     * unique serial identifier
     */
    private static final long serialVersionUID = 4579670117184769983L;


    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#reset()
     */
    @Override
    public void reset() {
        return;
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#evaluate(org.dlw.ai.blackboard.domain.Sentence)
     */
    @Override
    public void evaluate(Sentence sentence) {
        return;
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#notifyDependents(java.lang.String, org.dlw.ai.blackboard.domain.Assumption)
     */
    @Override
    public void notifyDependents(String direction, Assumption statement) {
        // TODO Auto-generated method stub
        super.notifyDependents(direction, statement);
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#compareTo(org.dlw.ai.blackboard.knowledge.KnowledgeSource)
     */
    @Override
    public int compareTo(KnowledgeSource o) {
        // TODO Auto-generated method stub
        return super.compareTo(o);
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#toString()
     */
    @Override
    public String toString() {
        return KnowledgeSourceConstants.LEGAL_STRING_KNOWLEDGE_SOURCE;
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object o) {
        // TODO Auto-generated method stub
        return super.equals(o);
    }

    /* (non-Javadoc)
     * @see org.dlw.ai.blackboard.knowledge.primitive.StringKnowledgeSource#hashCode()
     */
    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

}
