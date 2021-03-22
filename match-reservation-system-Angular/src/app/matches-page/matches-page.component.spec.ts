import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchesPageComponent } from './matches-page.component';

describe('MatchesPageComponent', () => {
  let component: MatchesPageComponent;
  let fixture: ComponentFixture<MatchesPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MatchesPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchesPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
