package com.example.notesapp.api

import com.example.notesapp.api.dto.RatingDto
import com.example.notesapp.api.dto.RecipeDto

/**
 * Created by Richard Gross on 2020-01-19
 */
object MockDataProvider {

    fun createMockLoadRecipesResp(): List<RecipeDto> =
        arrayListOf(
            RecipeDto().apply {
                name = "Ackee and Saltfish"
                duration = 10
                id = "58777f22b23541fc058c789f"
                score = 2.8f
            }, RecipeDto().apply {
                name = "Ackee with butter"
                duration = 15
                id = "58777f22b23541fc058c78a0"
                score = 3.5f
            }, RecipeDto().apply {
                name = "Ackee with hamburger"
                duration = 15
                id = "58777f22b23541fc058c78a0"
                score = 5.2f
            })

    fun createLoadRecipeDetailResp(): RecipeDto =
        RecipeDto().apply {
            name = "Ackee and Saltfish"
            duration = 10
            id = "58777f22b23541fc058c789f"
            score = 4.2f
            description =
                "Cover the saltfish in cold water. Let soak overnight (minimum 8 hours) changing the water several times (this removes most of the salt)\\n    Bring a pan of cold water to the boil and gently simmer the fish for 20 minutes (until the fish is tender).\\n    Chop the onion, sweet pepper, chilli pepper and tomato.\\n    Remove the fish from water and allow to cool.\\n    Remove all of bones and skin then flake the flesh of the fish.  \\n    Melt the butter in a frying pan and stir fry the onion, black pepper, sweet pepper, chilli and thyme for about 3 minutes. \\n    Add the tomatoes and flaked fish and stir-fry for another 10 minutes\\n    Add the Ackee and cook until hot throughout. Stir gently to avoid breaking-up the Ackee\\n    Serve with yam, green banana, fried dumplings and Irish potato\\n    \",\n"
            duration = 10
            ingredients = listOf(
                "1/2 lb Saltfish (dried, salted codfish)",
                "12 fresh ackees or 1 (drained) can of tinned ackees",
                " 1 medium onion",
                "1/2 tsp black pepper",
                "3 tbsp of butter",
                "1/2 a hot chilli pepper (ideally Scotch Bonnet)",
                "1 sweet pepper",
                "1 chopped tomato",
                "1 sprig fresh thyme or 1 tsp dried thyme",
                "2 cloves of garlic",
                "4 Scallion (or spring onions)",
                "6 Slices of bacon"
            )
            info = "Simple but fast and furiouso"
        }

    fun createMockAddRatingResp(): RatingDto = RatingDto().apply {
        score = 3
        recipe = "57a86a0e685b44580f60f6f2"
        id = "57a86f5e685b44580f60f6f6"
    }
}