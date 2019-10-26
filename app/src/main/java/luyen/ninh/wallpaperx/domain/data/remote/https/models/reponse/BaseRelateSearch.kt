package luyen.ninh.wallpaperx.domain.data.remote.https.models.reponse

class BaseRelateSearch {
    var collections = Collection()
    var related_searches = arrayListOf<Relate>()

}

class Collection {
    var results = arrayListOf<TagResult>()
}

class TagResult {
    var tags = arrayListOf<MyTag>()
}

class MyTag {
    var title = ""
}

class Relate {
    var title = ""
    var url = ""
}