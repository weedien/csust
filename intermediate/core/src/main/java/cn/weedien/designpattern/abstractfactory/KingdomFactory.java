package cn.weedien.designpattern.abstractfactory;

public interface KingdomFactory {

    Castle createCastle();

    King createKing();

    Army createArmy();

}
