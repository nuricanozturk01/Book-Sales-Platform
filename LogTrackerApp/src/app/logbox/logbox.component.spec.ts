import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LogboxComponent } from './logbox.component';

describe('LogboxComponent', () => {
  let component: LogboxComponent;
  let fixture: ComponentFixture<LogboxComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [LogboxComponent]
    });
    fixture = TestBed.createComponent(LogboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
