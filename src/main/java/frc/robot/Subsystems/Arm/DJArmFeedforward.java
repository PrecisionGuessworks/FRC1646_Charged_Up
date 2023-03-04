// Copyright (c) 2023 FRC 6328
// http://github.com/Mechanical-Advantage
//
// Use of this source code is governed by an MIT-style
// license that can be found in the LICENSE file at
// the root directory of this project.

package frc.robot.Subsystems.Arm;

import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.VecBuilder;
import edu.wpi.first.math.Vector;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N2;

/**
 * Calculates feedforward voltages for a double jointed arm.
 *
 * <p>https://www.chiefdelphi.com/t/whitepaper-two-jointed-arm-dynamics/423060
 * Adapted from 6328 ArmDynamics class
 */
public class DJArmFeedforward {
  private static final double g = 9.80665;
  
  private final JointConfig shoulderJoint;
  private final JointConfig elbowJoint;

  public DJArmFeedforward(JointConfig shoulderJoint, JointConfig elbowJoint) {
    this.shoulderJoint = shoulderJoint;
    this.elbowJoint = elbowJoint;
  }

  /** Calculates the joint voltages based on the joint positions (feedforward). */
  public Vector<N2> feedforward(Vector<N2> position) {
    return feedforward(position, VecBuilder.fill(0.0, 0.0), VecBuilder.fill(0.0, 0.0));
  }

  public Vector<N2> feedforward(Vector<N2> position, Vector<N2> velocity, Vector<N2> acceleration) {
    var torque =
        M(position)
            .times(acceleration)
            .plus(C(position, velocity).times(velocity))
            .plus(Tg(position));
    return VecBuilder.fill(
        shoulderJoint.motor.getVoltage(torque.get(0, 0), velocity.get(0, 0)),
        elbowJoint.motor.getVoltage(torque.get(1, 0), velocity.get(1, 0)));
  }


  private Matrix<N2, N2> M(Vector<N2> position) {
    var M = new Matrix<>(N2.instance, N2.instance);
    M.set(
        0,
        0,
        shoulderJoint.mass * Math.pow(shoulderJoint.cgRadius, 2.0)
            + elbowJoint.mass * (Math.pow(shoulderJoint.length, 2.0) + Math.pow(elbowJoint.cgRadius, 2.0))
            + shoulderJoint.moi
            + elbowJoint.moi
            + 2
                * elbowJoint.mass
                * shoulderJoint.length
                * elbowJoint.cgRadius
                * Math.cos(position.get(1, 0)));
    M.set(
        1,
        0,
        elbowJoint.mass * Math.pow(elbowJoint.cgRadius, 2.0)
            + elbowJoint.moi
            + elbowJoint.mass * shoulderJoint.length * elbowJoint.cgRadius * Math.cos(position.get(1, 0)));
    M.set(
        0,
        1,
        elbowJoint.mass * Math.pow(elbowJoint.cgRadius, 2.0)
            + elbowJoint.moi
            + elbowJoint.mass * shoulderJoint.length * elbowJoint.cgRadius * Math.cos(position.get(1, 0)));
    M.set(1, 1, elbowJoint.mass * Math.pow(elbowJoint.cgRadius, 2.0) + elbowJoint.moi);
    return M;
  }

  private Matrix<N2, N2> C(Vector<N2> position, Vector<N2> velocity) {
    var C = new Matrix<>(N2.instance, N2.instance);
    C.set(
        0,
        0,
        -elbowJoint.mass
            * shoulderJoint.length
            * elbowJoint.cgRadius
            * Math.sin(position.get(1, 0))
            * velocity.get(1, 0));
    C.set(
        1,
        0,
        elbowJoint.mass
            * shoulderJoint.length
            * elbowJoint.cgRadius
            * Math.sin(position.get(1, 0))
            * velocity.get(0, 0));
    C.set(
        0,
        1,
        -elbowJoint.mass
            * shoulderJoint.length
            * elbowJoint.cgRadius
            * Math.sin(position.get(1, 0))
            * (velocity.get(0, 0) + velocity.get(1, 0)));
    return C;
  }

  private Matrix<N2, N1> Tg(Vector<N2> position) {
    var Tg = new Matrix<>(N2.instance, N1.instance);
    Tg.set(
        0,
        0,
        (shoulderJoint.mass * shoulderJoint.cgRadius + elbowJoint.mass * shoulderJoint.length)
                * g
                * Math.cos(position.get(0, 0))
            + elbowJoint.mass
                * elbowJoint.cgRadius
                * g
                * Math.cos(position.get(0, 0) + position.get(1, 0)));
    Tg.set(
        1,
        0,
        elbowJoint.mass * elbowJoint.cgRadius * g * Math.cos(position.get(0, 0) + position.get(1, 0)));
    return Tg;
  }

}