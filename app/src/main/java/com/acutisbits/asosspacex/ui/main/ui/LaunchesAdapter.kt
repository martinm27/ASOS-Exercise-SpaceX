package com.acutisbits.asosspacex.ui.main.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import com.acutisbits.asosspacex.R
import com.acutisbits.asosspacex.coreui.BaseListAdapter
import com.acutisbits.asosspacex.coreui.BindingViewHolder
import com.acutisbits.asosspacex.databinding.ItemLaunchBinding
import com.acutisbits.asosspacex.imageloader.ImageQueryLoader
import com.acutisbits.asosspacex.ui.main.model.LaunchViewState

class LaunchesAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageQueryLoader: ImageQueryLoader
) : BaseListAdapter<LaunchViewState, LaunchesAdapter.LaunchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LaunchViewHolder =
        LaunchViewHolder(layoutInflater, parent, imageQueryLoader)

    override fun onBindViewHolder(holder: LaunchViewHolder, position: Int) = holder.render(getItem(position))

    class LaunchViewHolder(
        layoutInflater: LayoutInflater,
        parent: ViewGroup,
        private val imageQueryLoader: ImageQueryLoader
    ) : BindingViewHolder<LaunchViewState, ItemLaunchBinding>(ItemLaunchBinding.inflate(layoutInflater, parent, false)) {

        private var currentImageUrl: String? = null

        override fun ItemLaunchBinding.render(item: LaunchViewState) {
            with(item) {
                if (currentImageUrl?.equals(missionPatchImageUrl) != true) {
                    currentImageUrl = missionPatchImageUrl
                    imageQueryLoader.loadWithKey(
                        launchMissionImage,
                        missionPatchImageUrl,
                        R.drawable.ic_failed
                    )
                }

                launchDaysPropertyKey.text = daysKey
                launchMissionPropertyValue.text = missionName
                launchDateTimePropertyValue.text = missionDate
                launchRocketPropertyValue.text = rocketData
                launchDaysPropertyValue.text = daysValue
                launchSuccessImage.setImageResource(if (isLaunchedSuccessfully) R.drawable.ic_check else R.drawable.ic_failed)
            }
        }
    }
}
