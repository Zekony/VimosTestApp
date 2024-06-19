package com.zekony.feature.productinfo.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.zekony.domain.products.entity.DbProductItem
import com.zekony.feature.productinfo.shareProduct.SharingRepository
import com.zekony.resources.R
import com.zekony.utilities.Constants

@Composable
fun ItemInfoScreen(productItem: DbProductItem) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        AsyncImage(
            model = Constants.BASE_IMAGE_URL + productItem.imgPreview,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = stringResource(R.string.article, productItem.gcode), style = MaterialTheme.typography.labelSmall)

        Text(text = productItem.name, style = MaterialTheme.typography.labelMedium)

        Text(
            text = stringResource(R.string.priceAndUnits, productItem.price, productItem.units),
            style = MaterialTheme.typography.labelLarge
        )
        val context = LocalContext.current
        Button(
            onClick = {
                SharingRepository().shareProductInfo(
                    context.getString(R.string.nameАrticleShare, productItem.name, productItem.gcode.toString()),
                    context
                )
            },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = stringResource(R.string.share))
        }
    }
}