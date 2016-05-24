//
//  ViewController.h
//  HelloWorld
//
//  Created by Rohtash Singh on 8/15/14.
//  Copyright (c) 2014 Rohtash Singh. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ViewController : UIViewController
@property (strong, nonatomic) IBOutlet UILabel *lblHello;
- (IBAction)sayHello:(id)sender;

@end
