package be.issouf.mysoothe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import be.issouf.mysoothe.ui.theme.MySootheTheme
import java.util.Locale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MySootheTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MySootheApp()
                }
            }
        }
    }
}



private val alignYourBodyData = listOf(
    R.drawable.ab1_inversions to R.string.ab1_inversions,
    R.drawable.ab2_quick_yoga to R.string.ab2_quick_yoga,
    R.drawable.ab3_stretching to R.string.ab3_stretching,
    R.drawable.ab4_tabata to R.string.ab4_tabata,
    R.drawable.ab5_hiit to R.string.ab5_hiit,
    R.drawable.ab6_pre_natal_yoga to R.string.ab6_pre_natal_yoga
).map { DrawableStringPair(it.first, it.second) }

private val favoriteCollectionsData = listOf(
    R.drawable.fc1_short_mantras to R.string.fc1_short_mantras,
    R.drawable.fc2_nature_meditations to R.string.fc2_nature_meditations,
    R.drawable.fc3_stress_and_anxiety to R.string.fc3_stress_and_anxiety,
    R.drawable.fc4_self_massage to R.string.fc4_self_massage,
    R.drawable.fc5_overwhelmed to R.string.fc5_overwhelmed,
    R.drawable.fc6_nightly_wind_down to R.string.fc6_nightly_wind_down
).map { DrawableStringPair(it.first, it.second) }

private data class DrawableStringPair(
    @DrawableRes val drawable: Int,
    @StringRes val text: Int
)


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
){
    TextField(
        value = "",
        onValueChange = {},
        leadingIcon = {
                      Icon(
                          Icons.Default.Search,
                          contentDescription = null
                      )
        },
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        ),
        placeholder = {
                      Text(stringResource(id = R.string.placeholder_search))
        },
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)

    )
}

@Composable
fun AlignYourElement(
    @DrawableRes drawable : Int,
    @StringRes text : Int,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
        Text(
            stringResource(id = text),
            style = MaterialTheme.typography.h3,
            modifier = Modifier
                .paddingFromBaseline(top = 24.dp, bottom = 8.dp)
        )
        
    }
}

@Composable
fun FavoriteCollectionCard(
    @DrawableRes drawable: Int,
    @StringRes text: Int,
    modifier: Modifier = Modifier
){
    Surface() {
    BottomAppBar() {
        
    }        
    }
    Surface() {
        BottomAppBar() {
        }
    }
    Surface(
        shape = MaterialTheme.shapes.small,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.width(192.dp)
        ) {
            Image(
                painterResource(id = drawable),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )

            Text(
                stringResource(id = text),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
        
    }
}

@Composable
fun AlignYourBodyRow(
    modifier: Modifier = Modifier
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
    ){
        items(alignYourBodyData){item ->
            AlignYourElement(
                drawable = item.drawable,
                text = item.text
            )

        }
    }
}

@Composable
fun FavoriteCollectionGrid(
    modifier: Modifier = Modifier
){
     LazyHorizontalGrid(
         rows = GridCells.Fixed(2),
         contentPadding = PaddingValues(horizontal = 16.dp),
         horizontalArrangement = Arrangement.spacedBy(8.dp),
         modifier = modifier.height(120.dp)
     ){
         items(favoriteCollectionsData){ item ->
             FavoriteCollectionCard(
                 drawable = item.drawable,
                 text = item.text)
         }
     }
}


@Composable
fun HomeSection(
    @StringRes title : Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Column(modifier) {
        Text(
            stringResource(id = title).uppercase(Locale.getDefault()),
            style = MaterialTheme.typography.h2,
            modifier = Modifier
                .paddingFromBaseline(top = 10.dp, bottom = 8.dp)
                .padding(16.dp)
        )
        content()
        
    }
}


@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    Column(
        modifier
            .padding(vertical = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        SearchBar(Modifier.padding(horizontal = 16.dp))
        HomeSection(title = R.string.align_your_body){
            AlignYourBodyRow()
        }
        HomeSection(title = R.string.favorite_collections){
            FavoriteCollectionGrid()
        }

    }

}

@Composable
fun SootheBottomNavigation(modifier: Modifier = Modifier){

    BottomNavigation(
        backgroundColor = MaterialTheme.colors.background,
        modifier = modifier) {
        BottomNavigationItem(
            icon = { Icon(
                imageVector = Icons.Default.ShoppingCart,
                contentDescription = null)
                   },
            label = { Text(
                stringResource(R.string.bottom_navigation_home))
                    },
            selected = true,
            onClick = { /*TODO*/ }
            )
        BottomNavigationItem(
            icon = { Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null)
                   },
            label = { Text(
                stringResource(R.string.bottom_navigation_profile))
                    },
            selected = true,
            onClick = { /*TODO*/ }

        )
        
    }

}


@Composable
fun MySootheApp(){
    MySootheTheme {
        Scaffold(
            bottomBar = { SootheBottomNavigation()}
        ) {padding -> HomeScreen(Modifier.padding(padding))

        }
    }
}


@Preview(showBackground = true)
@Composable
fun MySootheAppPreview(){
    MySootheTheme {
        MySootheApp()
    }

}




//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview(){
//    MySootheTheme {
//        HomeScreen(
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//
//}
//
//

//@Preview(showBackground = true)
//@Composable
//fun HomeSection(){
//    MySootheTheme {
//        HomeSection(
//            title = R.string.align_your_body,
//            modifier = Modifier.padding(8.dp)
//        ){
//            AlignYourBodyRow()
//        }
//    }
//
//}
//
//
//@Preview(showBackground = true)
//@Composable
//fun FavoriteCollectionGrid(){
//    MySootheTheme {
//        FavoriteCollectionGrid(
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SootheBottomNavigationPreview(){
//    MySootheTheme {
//        SootheBottomNavigation(
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//
//}

//@Preview(showBackground = true)
//@Composable
//fun FavoriteCollectionCardPreview(){
//    MySootheTheme {
//        FavoriteCollectionCard(
//            drawable = R.drawable.fc2_nature_meditations,
//            text = R.string.fc2_nature_meditations,
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//
//}

//@Preview(showBackground = true)
//@Composable
//fun AlignYourElementPreview(){
//    MySootheTheme {
//        AlignYourElement(
//            drawable = R.drawable.ab1_inversions,
//            text = R.string.ab1_inversions,
//            modifier = Modifier.padding(8.dp)
//        )
//    }
//
//}
//
//@Preview(showBackground = true)
//@Composable
//fun SearchBarPreview(){
//    MySootheTheme {
//        SearchBar(Modifier.padding(8.dp))
//    }
//
//}