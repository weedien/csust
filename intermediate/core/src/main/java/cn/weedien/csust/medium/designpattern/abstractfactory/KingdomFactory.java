package cn.weedien.csust.medium.designpattern.abstractfactory;

public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();

}
