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
        @SpireInsertPatch(rloc = 3, localvars = {"rareCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng,
                                               @ByRef(type = "cards.CardGroup") Object[] rareCardPool) {
            if (AbstractDungeon.player != null) {
                CardGroup newRareGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newRareGroup.size() > 0) {
                    for (AbstractCard c : newRareGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        ((CardGroup) rareCardPool[0]).group.add(c);
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
        @SpireInsertPatch(rloc = 4, localvars = {"rareCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng,
                                               @ByRef(type = "cards.CardGroup") Object[] rareCardPool) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            for (AbstractCard c : ((CardGroup) rareCardPool[0]).group) {
                if (c.hasTag(CustomTagsEnum.SoleCard))
                    groupForRemove.group.add(c);
            }

            for (AbstractCard c : groupForRemove.group) {
                ((CardGroup) rareCardPool[0]).removeCard(c);
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
        @SpireInsertPatch(rloc = 10, localvars = {"uncommonCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng,
                                               @ByRef(type = "cards.CardGroup") Object[] uncommonCardPool) {
            if (AbstractDungeon.player != null) {
                CardGroup newUncommonGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newUncommonGroup.size() > 0) {
                    for (AbstractCard c : newUncommonGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        ((CardGroup) uncommonCardPool[0]).group.add(c);
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
        @SpireInsertPatch(rloc = 11, localvars = {"uncommonCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng,
                                               @ByRef(type = "cards.CardGroup") Object[] uncommonCardPool) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            for (AbstractCard c : ((CardGroup) uncommonCardPool[0]).group) {
                if (c.hasTag(CustomTagsEnum.SoleCard))
                    groupForRemove.group.add(c);
            }

            for (AbstractCard c : groupForRemove.group) {
                ((CardGroup) uncommonCardPool[0]).removeCard(c);
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
        @SpireInsertPatch(rloc = 19, localvars = {"commonCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng,
                                               @ByRef(type = "cards.CardGroup") Object[] commonCardPool) {
            if (AbstractDungeon.player != null) {
                CardGroup newCommonGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newCommonGroup.size() > 0) {
                    for (AbstractCard c : newCommonGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        ((CardGroup) commonCardPool[0]).group.add(c);
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
        @SpireInsertPatch(rloc = 20, localvars = {"commonCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               AbstractCard.CardType type,
                                               boolean useRng,
                                               @ByRef(type = "cards.CardGroup") Object[] commonCardPool) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            for (AbstractCard c : ((CardGroup) commonCardPool[0]).group) {
                if (c.hasTag(CustomTagsEnum.SoleCard))
                    groupForRemove.group.add(c);
            }

            for (AbstractCard c : groupForRemove.group) {
                ((CardGroup) commonCardPool[0]).removeCard(c);
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
        @SpireInsertPatch(rloc = 42, localvars = {"rareCardPool", "uncommonCardPool", "commonCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               @ByRef(type = "cards.CardGroup") Object[] rareCardPool,
                                               @ByRef(type = "cards.CardGroup") Object[] uncommonCardPool,
                                               @ByRef(type = "cards.CardGroup") Object[] commonCardPool
        ) {
            if (AbstractDungeon.player != null) {
                CardGroup newCommonGroup = returnSoleCard(rarity);

                System.out.println("=============唯一=============" + rarity);
                if (newCommonGroup.size() > 0) {
                    for (AbstractCard c : newCommonGroup.group) {
                        System.out.println(rarity + "唯一卡牌卡池加入：" + c.name);
                        if (rarity == AbstractCard.CardRarity.RARE) {
                            ((CardGroup) rareCardPool[0]).group.add(c);
                        }
                        if (rarity == AbstractCard.CardRarity.UNCOMMON) {
                            ((CardGroup) uncommonCardPool[0]).group.add(c);
                        }
                        if (rarity == AbstractCard.CardRarity.COMMON) {
                            ((CardGroup) commonCardPool[0]).group.add(c);
                        }
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
    public static class CardRewardGetCardPostPatch {
        @SpireInsertPatch(rloc = 43, localvars = {"rareCardPool", "uncommonCardPool", "commonCardPool"})
        public static SpireReturn<Void> Insert(AbstractCard.CardRarity rarity,
                                               @ByRef(type = "cards.CardGroup") Object[] rareCardPool,
                                               @ByRef(type = "cards.CardGroup") Object[] uncommonCardPool,
                                               @ByRef(type = "cards.CardGroup") Object[] commonCardPool
        ) {
            CardGroup groupForRemove = new CardGroup(CardGroup.CardGroupType.CARD_POOL);
            groupForRemove.clear();

            if (rarity == AbstractCard.CardRarity.RARE) {
                for (AbstractCard c : ((CardGroup) commonCardPool[0]).group) {
                    if (c.hasTag(CustomTagsEnum.SoleCard))
                        groupForRemove.group.add(c);
                }

                for (AbstractCard c : groupForRemove.group) {
                    ((CardGroup) rareCardPool[0]).removeCard(c);
                }
            }

            if (rarity == AbstractCard.CardRarity.UNCOMMON) {
                for (AbstractCard c : ((CardGroup) commonCardPool[0]).group) {
                    if (c.hasTag(CustomTagsEnum.SoleCard))
                        groupForRemove.group.add(c);
                }

                for (AbstractCard c : groupForRemove.group) {
                    ((CardGroup) uncommonCardPool[0]).removeCard(c);
                }
            }

            if (rarity == AbstractCard.CardRarity.COMMON) {
                for (AbstractCard c : ((CardGroup) commonCardPool[0]).group) {
                    if (c.hasTag(CustomTagsEnum.SoleCard))
                        groupForRemove.group.add(c);
                }

                for (AbstractCard c : groupForRemove.group) {
                    ((CardGroup) commonCardPool[0]).removeCard(c);
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
                if (rarity == AbstractCard.CardRarity.RARE && c.hasTag(CustomTagsEnum.SoleRare) && !AbstractDungeon.player.masterDeck.contains(c)) {
                    System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                    groupForReturn.group.add(c);
                }

                if (rarity == AbstractCard.CardRarity.UNCOMMON && c.hasTag(CustomTagsEnum.SoleUncommon) && !AbstractDungeon.player.masterDeck.contains(c)) {
                    System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                    groupForReturn.group.add(c);
                }

                if (rarity == AbstractCard.CardRarity.COMMON && c.hasTag(CustomTagsEnum.SoleCommon) && !AbstractDungeon.player.masterDeck.contains(c)) {
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
            if (rarity == AbstractCard.CardRarity.RARE && c.hasTag(CustomTagsEnum.SoleRare) && !AbstractDungeon.player.masterDeck.contains(c)) {
                System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                groupForReturn.group.add(c);
            }

            if (rarity == AbstractCard.CardRarity.UNCOMMON && c.hasTag(CustomTagsEnum.SoleUncommon) && !AbstractDungeon.player.masterDeck.contains(c)) {
                System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                groupForReturn.group.add(c);
            }

            if (rarity == AbstractCard.CardRarity.COMMON && c.hasTag(CustomTagsEnum.SoleCommon) && !AbstractDungeon.player.masterDeck.contains(c)) {
                System.out.println(rarity + "唯一卡牌临时卡池加入：" + c.name);
                groupForReturn.group.add(c);
            }
        }

        return groupForReturn;
    }

}


