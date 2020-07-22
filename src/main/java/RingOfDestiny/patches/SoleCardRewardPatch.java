package RingOfDestiny.patches;

import RingOfDestiny.RingOfDestiny;
import RingOfDestiny.character.MagicBullet;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.helpers.ModHelper;

import static basemod.BaseMod.getModdedCharacters;
import static com.megacrit.cardcrawl.dungeons.AbstractDungeon.*;


public class SoleCardRewardPatch {
//================================
//================================
//================================
//================================商店奖励

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getCardFromPool",
            paramtypez = {AbstractCard.CardRarity.class, AbstractCard.CardType.class, boolean.class}
    )
    public static class RareCardPoolPrePatch {
        @SpireInsertPatch(rloc = 3)
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng) {
            if (AbstractDungeon.player != null) {
                CardGroup newRareGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newRareGroup.size() > 0) {
                    for (AbstractCard c : newRareGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        rareCardPool.group.add(c);
                    }
                } else {
                    System.out.println(rarity + "唯一牌为空");
                }

            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getCardFromPool",
            paramtypez = {AbstractCard.CardRarity.class, AbstractCard.CardType.class, boolean.class}
    )
    public static class RareCardPoolPostPatch {
        @SpireInsertPatch(rloc = 4)
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            for (AbstractCard c : rareCardPool.group) {
                if (c.hasTag(CustomTagsEnum.SoleCard))
                    groupForRemove.group.add(c);
            }

            for (AbstractCard c : groupForRemove.group) {
                rareCardPool.removeCard(c);
            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getCardFromPool",
            paramtypez = {AbstractCard.CardRarity.class, AbstractCard.CardType.class, boolean.class}
    )
    public static class UncommonCardPoolPrePatch {
        @SpireInsertPatch(rloc = 10)
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng) {
            if (AbstractDungeon.player != null) {
                CardGroup newUncommonGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newUncommonGroup.size() > 0) {
                    for (AbstractCard c : newUncommonGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        uncommonCardPool.group.add(c);
                    }
                } else {
                    System.out.println(rarity + "唯一牌为空");
                }

            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getCardFromPool",
            paramtypez = {AbstractCard.CardRarity.class, AbstractCard.CardType.class, boolean.class}
    )
    public static class UncommonCardPoolPostPatch {
        @SpireInsertPatch(rloc = 11)
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            for (AbstractCard c : uncommonCardPool.group) {
                if (c.hasTag(CustomTagsEnum.SoleCard))
                    groupForRemove.group.add(c);
            }

            for (AbstractCard c : groupForRemove.group) {
                uncommonCardPool.removeCard(c);
            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getCardFromPool",
            paramtypez = {AbstractCard.CardRarity.class, AbstractCard.CardType.class, boolean.class}
    )
    public static class CommonCardPoolPrePatch {
        @SpireInsertPatch(rloc = 19)
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng) {
            if (AbstractDungeon.player != null) {
                CardGroup newCommonGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newCommonGroup.size() > 0) {
                    for (AbstractCard c : newCommonGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        commonCardPool.group.add(c);
                    }
                } else {
                    System.out.println(rarity + "唯一牌为空");
                }

            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getCardFromPool",
            paramtypez = {AbstractCard.CardRarity.class, AbstractCard.CardType.class, boolean.class}
    )
    public static class CommonCardPoolPostPatch {
        @SpireInsertPatch(rloc = 20)
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            for (AbstractCard c : commonCardPool.group) {
                if (c.hasTag(CustomTagsEnum.SoleCard))
                    groupForRemove.group.add(c);
            }

            for (AbstractCard c : groupForRemove.group) {
                commonCardPool.removeCard(c);
            }

            return SpireReturn.Continue();
        }
    }

    //================================
//================================
//================================
//================================商店奖励


    //================================
//================================
//================================
//================================卡牌奖励
    @SpirePatch(
            clz = CardLibrary.class,
            method = "getAnyColorCard",
            paramtypez = {AbstractCard.CardRarity.class}
    )
    public static class CardRewardGetAnyColorCardPatch {
        @SpireInsertPatch(rloc = 10, localvars = {"anyCard"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               @ByRef(type = "cards.CardGroup") Object[] anyCard) {
            if (AbstractDungeon.player != null) {
                CardGroup newCommonGroup = returnAnySoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newCommonGroup.size() > 0) {
                    for (AbstractCard c : newCommonGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        ((CardGroup) anyCard[0]).group.add(c);
                    }
                } else {
                    System.out.println(rarity + "唯一牌为空");
                }

            }

            return SpireReturn.Continue();
        }
    }

    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class CardRewardGetCardPrePatch {
        @SpireInsertPatch(rloc = 42, localvars = {"rarity"})
        public static SpireReturn<Void> Insert(@ByRef(type = "cards.AbstractCard.CardRarity") Object[] rarity) {
            if (AbstractDungeon.player != null) {
                CardGroup newCommonGroup = returnSoleCard((AbstractCard.CardRarity) rarity[0]);

                System.out.println("=============唯一=============" + rarity[0]);
                if (newCommonGroup.size() > 0) {
                    for (AbstractCard c : newCommonGroup.group) {
                        System.out.println(rarity[0] + "唯一卡牌卡池加入：" + c.name);
                        if ((AbstractCard.CardRarity) rarity[0] == AbstractCard.CardRarity.RARE) {
                            rareCardPool.group.add(c);
                        }
                        if ((AbstractCard.CardRarity) rarity[0] == AbstractCard.CardRarity.UNCOMMON) {
                            uncommonCardPool.group.add(c);
                        }
                        if ((AbstractCard.CardRarity) rarity[0] == AbstractCard.CardRarity.COMMON) {
                            commonCardPool.group.add(c);
                        }
                    }
                } else {
                    System.out.println(rarity[0] + "唯一牌为空");
                }

            }

            return SpireReturn.Continue();
        }
    }


    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "getRewardCards"
    )
    public static class CardRewardGetCardPostPatch {
        @SpireInsertPatch(rloc = 43, localvars = {"rarity"})
        public static SpireReturn<Void> Insert(@ByRef(type = "cards.AbstractCard.CardRarity") Object[] rarity) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);

            if ((AbstractCard.CardRarity) rarity[0] == AbstractCard.CardRarity.RARE) {
                groupForRemove.clear();
                for (AbstractCard c : rareCardPool.group) {
                    if (c.hasTag(CustomTagsEnum.SoleCard))
                        groupForRemove.group.add(c);
                }

                for (AbstractCard c : groupForRemove.group) {
                    rareCardPool.removeCard(c);
                }
            }

            if ((AbstractCard.CardRarity) rarity[0] == AbstractCard.CardRarity.UNCOMMON) {
                groupForRemove.clear();
                for (AbstractCard c : commonCardPool.group) {
                    if (c.hasTag(CustomTagsEnum.SoleCard))
                        groupForRemove.group.add(c);
                }

                for (AbstractCard c : groupForRemove.group) {
                    uncommonCardPool.removeCard(c);
                }
            }

            if ((AbstractCard.CardRarity) rarity[0] == AbstractCard.CardRarity.COMMON) {
                groupForRemove.clear();
                for (AbstractCard c : commonCardPool.group) {
                    if (c.hasTag(CustomTagsEnum.SoleCard))
                        groupForRemove.group.add(c);
                }

                for (AbstractCard c : groupForRemove.group) {
                    commonCardPool.removeCard(c);
                }
            }


            return SpireReturn.Continue();
        }
    }


    //================================
//================================
//================================
//================================卡牌奖励


    public static CardGroup returnSoleCard(AbstractCard.CardRarity rarity) {
        CardGroup groupForReturn = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        groupForReturn.clear();


        if (AbstractDungeon.player.chosenClass == AbstractPlayerEnum.MagicBullet
                || ModHelper.isModEnabled("Diverse")
                || ModHelper.isModEnabled("MagicBullet" + "Modded Character Cards")) {
            System.out.println(rarity + "唯一卡牌临时卡池开始加入");
            for (AbstractCard c : RingOfDestiny.MB_SoleCards) {
                if (rarity == AbstractCard.CardRarity.RARE && c.hasTag(CustomTagsEnum.SoleRare) && AbstractDungeon.player.masterDeck.findCardById(c.cardID) == null) {
                    System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                    groupForReturn.group.add(c);
                }

                if (rarity == AbstractCard.CardRarity.UNCOMMON && c.hasTag(CustomTagsEnum.SoleUncommon) && AbstractDungeon.player.masterDeck.findCardById(c.cardID) == null) {
                    System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                    groupForReturn.group.add(c);
                }

                if (rarity == AbstractCard.CardRarity.COMMON && c.hasTag(CustomTagsEnum.SoleCommon) && AbstractDungeon.player.masterDeck.findCardById(c.cardID) == null) {
                    System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                    groupForReturn.group.add(c);
                }
            }
        }


        return groupForReturn;
    }


    public static CardGroup returnAnySoleCard(AbstractCard.CardRarity rarity) {
        CardGroup groupForReturn = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
        groupForReturn.clear();

        System.out.println(rarity + "唯一卡牌临时卡池开始加入");
        for (AbstractCard c : RingOfDestiny.MB_SoleCards) {
            if (rarity == AbstractCard.CardRarity.RARE && c.hasTag(CustomTagsEnum.SoleRare) && AbstractDungeon.player.masterDeck.findCardById(c.cardID) == null) {
                System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                groupForReturn.group.add(c);
            }

            if (rarity == AbstractCard.CardRarity.UNCOMMON && c.hasTag(CustomTagsEnum.SoleUncommon) && AbstractDungeon.player.masterDeck.findCardById(c.cardID) == null) {
                System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                groupForReturn.group.add(c);
            }

            if (rarity == AbstractCard.CardRarity.COMMON && c.hasTag(CustomTagsEnum.SoleCommon) && AbstractDungeon.player.masterDeck.findCardById(c.cardID) == null) {
                System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                groupForReturn.group.add(c);
            }
        }

        return groupForReturn;
    }
}


