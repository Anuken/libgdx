/*******************************************************************************
 * Copyright 2011 See AUTHORS file.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.badlogic.gdx.physics.box2d.joints;

import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;

/**
 * Gear joint definition. This definition requires two existing revolute or prismatic joints (any combination will work). The
 * provided joints must attach a dynamic body to a static body.
 */
public class GearJointDef extends JointDef{
    /** The first revolute/prismatic joint attached to the gear joint. */
    public Joint joint1 = null;
    /** The second revolute/prismatic joint attached to the gear joint. */
    public Joint joint2 = null;
    /**
     * The gear ratio.
     * @see GearJoint for explanation.
     */
    public float ratio = 1;

    public GearJointDef(){
        type = JointType.GearJoint;
    }

    @Override
    public org.jbox2d.dynamics.joints.JointDef toJBox2d(){
        org.jbox2d.dynamics.joints.GearJointDef jd = new org.jbox2d.dynamics.joints.GearJointDef();
        jd.bodyA = bodyA.body;
        jd.bodyB = bodyB.body;
        jd.collideConnected = collideConnected;
        jd.joint1 = joint1.getJBox2DJoint();
        jd.joint2 = joint2.getJBox2DJoint();
        jd.ratio = ratio;
        jd.type = org.jbox2d.dynamics.joints.JointType.GEAR;
        return jd;
    }
}
