package com.vinicius.githubexplorerapp.domain.model

import android.os.Parcel
import android.os.Parcelable

data class UserFollower(
    val id: Int,
    val login: String?,
    val avatarUrl: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(login)
        parcel.writeString(avatarUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserFollower> {
        override fun createFromParcel(parcel: Parcel): UserFollower {
            return UserFollower(parcel)
        }

        override fun newArray(size: Int): Array<UserFollower?> {
            return arrayOfNulls(size)
        }
    }
}
