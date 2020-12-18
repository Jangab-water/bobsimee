package com.rjh.myproject;

public class FoodDTO {
    private String foods;
    int rain;
    int cold;
    int hot;
    int stress;
    int good;
    int fight;
    int friend;
    int tasty;
    int reward;
    int congratulation;

    public FoodDTO() {
    }

    public FoodDTO(String foods, int rain, int cold, int hot, int stress, int good, int fight, int friend, int tasty, int reward, int congratulation) {
        this.foods = foods;
        this.rain = rain;
        this.cold = cold;
        this.hot = hot;
        this.stress = stress;
        this.good = good;
        this.fight = fight;
        this.friend = friend;
        this.tasty = tasty;
        this.reward = reward;
        this.congratulation = congratulation;
    }

    public String getFoods() {
        return foods;
    }

    public void setFoods(String foods) {
        this.foods = foods;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getCold() {
        return cold;
    }

    public void setCold(int cold) {
        this.cold = cold;
    }

    public int getHot() {
        return hot;
    }

    public void setHot(int hot) {
        this.hot = hot;
    }

    public int getStress() {
        return stress;
    }

    public void setStress(int stress) {
        this.stress = stress;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getFight() {
        return fight;
    }

    public void setFight(int fight) {
        this.fight = fight;
    }

    public int getFriend() {
        return friend;
    }

    public void setFriend(int friend) {
        this.friend = friend;
    }

    public int getTasty() {
        return tasty;
    }

    public void setTasty(int tasty) {
        this.tasty = tasty;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public int getCongratulation() {
        return congratulation;
    }

    public void setCongratulation(int congratulation) {
        this.congratulation = congratulation;
    }

    @Override
    public String toString() {
        return "FoodDTO{" +
                "foods='" + foods + '\'' +
                ", rain=" + rain +
                ", cold=" + cold +
                ", hot=" + hot +
                ", stress=" + stress +
                ", good=" + good +
                ", fight=" + fight +
                ", friend=" + friend +
                ", tasty=" + tasty +
                ", reward=" + reward +
                ", congratulation=" + congratulation +
                '}';
    }
}
