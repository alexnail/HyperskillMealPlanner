package mealplanner;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MealStorage {

    private static final String INSERT_MEAL_SQL = "INSERT INTO meals(category, meal) VALUES(?, ?)";
    private static final String SELECT_MEAL_ID_SQL = "SELECT meal_id FROM meals WHERE category=? and meal=?";
    private static final String INSERT_INGREDIENT_SQL = "INSERT INTO ingredients(meal_id, ingredient) VALUES(?, ?)";
    public static final String SELECT_ALL_MEALS_SQL = """
        SELECT m.meal_id, m.category, m.meal, i.ingredient_id, i.ingredient
        FROM meals m JOIN ingredients i ON m.meal_id = i.meal_id
        ORDER BY m.meal_id, i.ingredient_id
    """;
    public static final String SELECT_MEALS_IN_CATEGORY_SQL = """
        SELECT m.meal_id, m.category, m.meal, i.ingredient_id, i.ingredient
        FROM meals m JOIN ingredients i ON m.meal_id = i.meal_id
        WHERE m.category=?
        ORDER BY m.meal_id, i.ingredient_id
    """;

    private static Connection connection;

    public static void setConnection(Connection connection) {
        MealStorage.connection = connection;
    }

    public static void save(Meal meal) {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_MEAL_SQL)){
           ps.setString(1, meal.getCategory());
           ps.setString(2, meal.getName());
           ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        int mealId;
        try (PreparedStatement ps = connection.prepareStatement(SELECT_MEAL_ID_SQL)) {
            ps.setString(1, meal.getCategory());
            ps.setString(2, meal.getName());
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            mealId = resultSet.getInt("meal_id");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        if (mealId > 0) {
            try (PreparedStatement ps = connection.prepareStatement(INSERT_INGREDIENT_SQL)) {
                for (String ingredient: meal.getIngredients()) {
                    ps.setInt(1, mealId);
                    ps.setString(2, ingredient);
                    ps.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Meal> getAllMeals() {
        return getList(SELECT_ALL_MEALS_SQL);
    }

    private static List<Meal> getList(String sql, Object... parameters) {
        List<MealIngredientRow> rows = new LinkedList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            setParameters(parameters, ps);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int mealId = resultSet.getInt("meal_id");
                String category = resultSet.getString("category");
                String mealName = resultSet.getString("meal");
                int ingredientId = resultSet.getInt("ingredient_id");
                String ingredient = resultSet.getString("ingredient");
                rows.add(new MealIngredientRow(mealId, category, mealName, ingredientId, ingredient));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Map<Integer, List<MealIngredientRow>> grouped = rows.stream()
                .collect(Collectors.groupingBy(MealIngredientRow::mealId));

        return grouped.values().stream()
                .map(v -> {
                            MealIngredientRow first = v.get(0);
                            Meal.Builder builder = new Meal.Builder()
                                    .withCategory(first.category())
                                    .withName(first.meal());
                            builder.withIngredients(v.stream()
                                    .map(MealIngredientRow::ingredient)
                                    .toList());
                            return builder.build();
                        }
                ).toList();
    }

    private static void setParameters(Object[] parameters, PreparedStatement ps) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            Object parameter = parameters[i];
            if (parameter instanceof String strObject) {
                ps.setString(i + 1, strObject);
            } else if (parameter instanceof Integer intObject) {
                ps.setInt(i + 1, intObject);
            } else {
                System.out.println("Unknown parameter type: " + parameter.getClass().getName());
            }
        }
    }

    public static List<Meal> getMeals(String category) {
        return getList(SELECT_MEALS_IN_CATEGORY_SQL, category);
    }
}

record MealIngredientRow(
        Integer mealId,
        String category,
        String meal,
        Integer ingredientId,
        String ingredient
){}
