//
//  TestViewController.swift
//  TestProj
//
//  Created by Aleksey Mikhailov on 23/06/2019.
//  Copyright © 2019 IceRock Development. All rights reserved.
//

import UIKit
import MultiPlatformLibrary
import Alamofire

class TestViewController: UIViewController {
  private var presenter: TestViewPresenter!
  
  @IBOutlet var textLabel: UITextView!
  
  override func viewDidLoad() {
    super.viewDidLoad()
    
    let nativeInterface = NativeCall()
    let networkCallTest = NativeNetworkCallTest(nativeInterface: nativeInterface)
    presenter = TestViewPresenter(networkCallTest: networkCallTest, view: self)
  }
  
  @IBAction func onButtonPressed() {
    presenter.onButtonPressed()
  }
}

extension TestViewController: TestViewPresenterView {
  func showMessage(string: String) {
    textLabel.text = string
    
    guard let presenter = self.presenter else { return }
    for _ in 0 ... 3 {
      DispatchQueue.main.async {
        presenter.onButtonPressed()
      }
    }
  }
}

struct Direction: Decodable {
  let routes: [Route]
}

struct Route: Decodable {
  let legs: [Leg]
  let overview_polyline: OverviewPolyline
}

struct Leg: Decodable {
  let start_location: DirectionLocation
  let end_location: DirectionLocation
  let steps: [Step]
}

struct Step: Decodable {
  let end_location: DirectionLocation
}

struct DirectionLocation: Decodable {
  let lat: Double
  let lng: Double
}

struct OverviewPolyline: Decodable {
  let points: String
}


class NativeCall: NSObject, NativeCallInterface {
  func testCall(callback: @escaping (String?, KotlinThrowable?) -> Void) {
    let googleApiKey = "PLACE_API_KEY_HERE"
    let random = Float.random(in: 0.0 ... 10.0)
    let url = URL(string: "https://maps.googleapis.com/maps/api/directions/json?origin=55.061136,82.936454&destination=55.06793021689708,\(82 + random).06793021689708&key=\(googleApiKey)")
    
    Alamofire.request(url!, method: .get, parameters: nil, encoding: URLEncoding.default, headers: nil)
      .responseData { (response) in
        if let data = response.data {
          do {
            let direction = try JSONDecoder().decode(Direction.self, from: data)
            
            print("parsing done \(direction)")
            
            callback(String(describing: direction), nil)
          } catch let error as NSError {
            print("parsing failed \(error)")
            callback(nil, KotlinThrowable(message: error.localizedDescription))
          }
        } else if let error = response.error {
          print("error \(error)")
          callback(nil, KotlinThrowable(message: error.localizedDescription))
        } else {
          print("unknown result")
          callback(nil, KotlinThrowable(message: "unknown result at \(response)"))
        }
    }
  }
}

