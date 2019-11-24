//
//  ViewController.swift
//  MultiplatformMovies
//
//  Created by Tobias Heine on 23.11.19.
//  Copyright © 2019 Tobias Heine. All rights reserved.
//

import UIKit
import SDWebImage
import MoviesFrontend

class MovieGalleryViewController: UICollectionViewController, MoviesPresenterView {
    
    private let cellIdentifier = "movie_poster"
    private var movies : [DataMovieGalleryItem] = []
    private let presenter = DependencyProvider().providePresenter()
    
    func render(movieGallery: DataMovieGallery) {
        print(movieGallery.items.count)
        movies = movieGallery.items
        collectionView?.reloadData()
    }
    
    func showError(throwable: KotlinThrowable) {
        print(throwable.message!)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        collectionView?.backgroundColor = .white
        collectionView?.register(MoviePosterCell.self, forCellWithReuseIdentifier: cellIdentifier)
        presenter.bind(view: self)
    }
}

//MARK: - UICollectionViewDatasource
extension MovieGalleryViewController {
    
    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
            return movies.count
    }
    
    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: cellIdentifier, for: indexPath) as! MoviePosterCell
        let movie = movies[indexPath.row]
        cell.posterImage.sd_setImage(with: URL(string: movie.thumbnailUrl), placeholderImage: nil)
        return cell
    }
    
}

extension MovieGalleryViewController: UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: collectionView.frame.width/2, height: 310)
    }
}

class MoviePosterCell: UICollectionViewCell {
    let posterImage = UIImageView()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        posterImage.contentMode = .scaleAspectFit
        posterImage.clipsToBounds = true
        posterImage.isUserInteractionEnabled = false
        
        contentView.addSubview(posterImage)
    }
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        posterImage.frame = contentView.frame
    }
}

