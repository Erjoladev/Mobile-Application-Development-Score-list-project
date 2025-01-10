import android.os.Parcel
import android.os.Parcelable

data class Joueur(
    val nom: String,
    var pointage: Int
) : Parcelable {

    private constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nom)
        parcel.writeInt(pointage)
    }

    override fun describeContents(): Int = 0

    companion object CREATOR : Parcelable.Creator<Joueur> {
        override fun createFromParcel(parcel: Parcel): Joueur = Joueur(parcel)
        override fun newArray(size: Int): Array<Joueur?> = arrayOfNulls(size)
    }
}
