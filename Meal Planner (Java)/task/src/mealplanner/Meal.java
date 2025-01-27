package mealplanner;

import java.util.List;

public class Meal {
    private Integer id;
    private String category;
    private String name;
    private List<String> ingredients;

    public Integer getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public static class Builder {

        private Integer id;
        private String category;
        private String name;
        private List<String> ingredients;

        public Builder withId(Integer id) {
            this.id = id;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withIngredients(List<String> ingredients) {
            this.ingredients = ingredients;
            return this;
        }

        public Meal build() {
            Meal meal = new Meal();
            meal.id = id;
            meal.category = category;
            meal.name = name;
            meal.ingredients = ingredients;
            return meal;
        }
    }
}
