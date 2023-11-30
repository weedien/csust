package cn.weedien.designpattern.abstractfactory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Kingdom {

    private King king;
    private Castle castle;
    private Army army;

    /**
     * KingdomFactory 的工厂
     */
    public static class FactoryMaker {

        /**
         * 不同 Kingdom 的枚举类型
         */
        public enum KingdomType {
            ELF, ORC
        }

        /**
         * 创建 KingdomFactory 实例的工厂方法
         * Elf 和 Orc 对应不同的 KingdomFactory
         */
        public static KingdomFactory makeFactory(KingdomType type) {
            return switch (type) {
                case ELF -> new ElfKingdomFactory();
                case ORC -> new OrcKingdomFactory();
                default -> throw new IllegalArgumentException("KingdomType not supported.");
            };
        }
    }
}
