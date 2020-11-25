package com.jhughes.eznews.domain.model

import androidx.annotation.StringRes
import com.jhughes.eznews.R

enum class Country(val countryCode: String, @StringRes val res: Int) {
    UNITED_ARAB_EMIRATES("ae", R.string.unsupported),
    ARGENTINA("ar", R.string.unsupported),
    AUSTRIA("at", R.string.unsupported),
    AUSTRALIA("au", R.string.australia),
    BELGIUM("be", R.string.unsupported),
    BULGARIA("bg", R.string.unsupported),
    BRAZIL("br", R.string.brazil),
    CANADA("ca", R.string.canada),
    SWITZERLAND("ch", R.string.unsupported),
    CHINA("cn", R.string.china),
    COLUMBIA("co", R.string.unsupported),
    CUBA("cu", R.string.unsupported),
    CZECHIA("cz", R.string.unsupported),
    GERMANY("de", R.string.germany),
    EGYPT("eg", R.string.unsupported),
    FRANCE("fr", R.string.france),
    UNITED_KINGDOM("gb", R.string.united_kingdom),
    GREECE("gr", R.string.unsupported),
    HONG_KONG("hk", R.string.hong_kong),
    HUNGARY("hu", R.string.unsupported),
    INDONESIA("id", R.string.unsupported),
    IRELAND("ie", R.string.ireland),
    ISRAEL("il", R.string.unsupported),
    INDIA("in", R.string.unsupported),
    ITALY("it", R.string.unsupported),
    JAPAN("jp", R.string.japan),
    KOREA("kr", R.string.unsupported),
    LITHUANIA("lt", R.string.unsupported),
    LATVIA("lv", R.string.unsupported),
    MOROCCO("ma", R.string.unsupported),
    MEXICO("mx", R.string.unsupported),
    MALAYSIA("my", R.string.unsupported),
    NIGERIA("ng", R.string.unsupported),
    NETHERLANDS("nl", R.string.unsupported),
    NORWAY("no", R.string.unsupported),
    NEW_ZEALAND("nz", R.string.unsupported),
    PHILIPPINES("ph", R.string.unsupported),
    POLAND("pl", R.string.unsupported),
    PORTUGAL("pt", R.string.unsupported),
    ROMANIA("ro", R.string.unsupported),
    SERBIA("rs", R.string.unsupported),
    RUSSIA("ru", R.string.unsupported),
    SAUDI_ARABIA("sa", R.string.unsupported),
    SWEDEN("se", R.string.unsupported),
    SINGAPORE("sg", R.string.unsupported),
    SLOVENIA("si", R.string.unsupported),
    SLOVAKIA("sk", R.string.unsupported),
    THAILAND("th", R.string.unsupported),
    TURKEY("tr", R.string.unsupported),
    TAIWAN("tw", R.string.unsupported),
    UKRAINE("ua", R.string.unsupported),
    USA("us", R.string.usa),
    VENEZUELA("ve", R.string.unsupported),
    SOUTH_AFRICA("za", R.string.south_africa);

    companion object {
        fun usableSubSet(): Set<Country> {
            return setOf(
                UNITED_KINGDOM,
                USA,
                FRANCE,
                GERMANY,
                AUSTRALIA,
                CHINA,
                JAPAN,
                CANADA,
                SOUTH_AFRICA,
                IRELAND,
                BRAZIL,
                HONG_KONG
            )
        }
    }
}

