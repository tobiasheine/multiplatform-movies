//
//  ViewController.swift
//  MultiplatformMovies
//
//  Created by Tobias Heine on 23.11.19.
//  Copyright © 2019 Tobias Heine. All rights reserved.
//

import UIKit
import MoviesFrontend

class ViewController: UIViewController, MoviesPresenterView {
    
    let presenter = DependencyProvider().providePresenter()
    
    func render(movieGallery: DataMovieGallery) {
        print(movieGallery.items.count)
    }
    
    func showError(throwable: KotlinThrowable) {
        print(throwable.message!)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.bind(view: self)
    }


}

